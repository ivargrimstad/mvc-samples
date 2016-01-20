/*
 * The MIT License
 *
 * Copyright 2015 Ivar Grimstad (ivar.grimstad@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.agilejava.mvc.prg;

import de.chkal.mvctoolbox.core.Toolbox;
import de.chkal.mvctoolbox.core.message.MvcMessage;
import eu.agilejava.mvc.service.ReservationService;
import static java.util.stream.Collectors.toList;
import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.CsrfValid;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import static javax.validation.executable.ExecutableType.NONE;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Controller
@Path("reservations")
public class ReservationController {
    
    @Inject
    private Reservation reservation;
    
    @Inject
    private ReservationService reservationService;
    
    @Inject
    private BindingResult br;
    
    @Inject
    private Messages messages;
    
    @Inject
    private Toolbox mvcToolbox;
    
    @View("reservation.jsp")
    @GET
    @Path("new")
    public void emptyReservation() {
    }
    
    @CsrfValid
    @POST
    @Path("new")
    @ValidateOnExecution(type = NONE)
    public Response createReservation(@Valid @BeanParam ReservationFormBean form) {
        
        reservation.setId(form.getId());
        reservation.setName(form.getName());
        reservation.setCount(form.getCount());
        reservation.setDate(form.getDate());
        reservation.setOutside(form.isOutside());

        if (br.isFailed()) {
            messages.setErrors(
                    br.getAllViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(toList()));
            
            mvcToolbox.getMessages().add(br);
            mvcToolbox.getMessages().add(new MvcMessage(MvcMessage.Severity.ERROR, null, "A Global message!"));
            
            return Response.status(BAD_REQUEST).entity("reservation.jsp").build();
        }
        
        reservationService.save(reservation);
        
        return Response.ok("redirect:confirmation").build();        
    }
}
