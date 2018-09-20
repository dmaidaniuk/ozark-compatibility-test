package io.github.ozark.samples.compatibility;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Controller
@Path("inject")
public class InjectParamsController {

    @Inject
    private Models models;

    @QueryParam("fieldValue")
    private String fieldValue;

    @GET
    @Path("field")
    public Response fieldParam() {
        models.put("value", fieldValue);
        return Response.ok().entity("/WEB-INF/jsp/params.jsp").build();
    }
}
