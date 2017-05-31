package io.github.ozark.samples.compatibility;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Controller
@Path("validation-test")
public class ValitationTestController {

    @Inject
    private BindingResult br;

    @Inject
    Models models;

    @GET
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String sayHello(@QueryParam("name") @Size(min = 1) String name) {
        this.models.put("text", "Hello " + name);
        return "/WEB-INF/jsp/validation.jsp";
    }

}