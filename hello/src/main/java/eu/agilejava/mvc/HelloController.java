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
package eu.agilejava.mvc;

import java.util.Set;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Path("hello")
@Controller
public class HelloController {

    @Inject
    private Models models;

    @Inject
    private BindingResult validationResult;

    @GET
    @Path("{name}")
    @View("hello.jsp")
    public void view(@PathParam("name") String name) {

        models.put("name", name);
    }

    @POST
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response formPost(@Valid @BeanParam HelloBean form) {

        if (validationResult.isFailed()) {
            final Set<ConstraintViolation<?>> set = validationResult.getAllViolations();
            final ConstraintViolation<?> cv = set.iterator().next();
            final String property = cv.getPropertyPath().toString();

            models.put("property", property.substring(property.lastIndexOf('.') + 1));
            models.put("value", cv.getInvalidValue());
            models.put("message", cv.getMessage());

            return Response.status(BAD_REQUEST).entity("error.jsp").build();
        }

        models.put("name", form.getFirstName() + " " + form.getLastName());

        return Response.status(OK).entity("hello.jsp").build();
    }
}
