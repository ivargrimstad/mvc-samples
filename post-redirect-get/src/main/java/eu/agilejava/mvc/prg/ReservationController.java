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

import eu.agilejava.mvc.service.ReservationService;
import static java.util.stream.Collectors.toList;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.View;
import javax.mvc.binding.BindingResult;
import javax.mvc.security.CsrfProtected;
import javax.validation.Valid;
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

    @GET
    @View("reservation.jsp")
    @Path("new")
    public void emptyReservation() {
    }

    @CsrfProtected
    @POST
    @Path("new")
    public Response createReservation(@Valid @BeanParam ReservationFormBean form) {

        reservation.setId(form.getId());
        reservation.setName(form.getContact());
        reservation.setCount(form.getCount());
        reservation.setDate(form.getDate());
        reservation.setOutside(form.isOutside());

        if (br.isFailed()) {
            messages.setErrors(
                    br.getAllErrors().stream()
                            .collect(toList()));
            
            return Response.status(BAD_REQUEST).entity("reservation.jsp").build();
        }

        reservationService.save(reservation);

        return Response.ok("redirect:confirmation").build();
    }
}
