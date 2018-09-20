package io.github.ozark.samples.compatibility;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Controller
@Path("validation-test")
public class ValidationTestController {

    @Inject
    private BindingResult br;

    @Inject
    private Models models;

    @GET
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String sayHello(@QueryParam("name") @Size(min = 1) @Pattern(regexp = "[A-Z][a-zA-Z]*") String name) {
        if (br.isFailed()) {
            this.models.put("bindingResult", br);
            return "/WEB-INF/jsp/error.jsp";
        }
        this.models.put("text", "Hello " + name);
        return "/WEB-INF/jsp/validation.jsp";
    }

}