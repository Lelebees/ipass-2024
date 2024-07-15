package nl.lelebees.boekmanager.api.hello;

import nl.lelebees.boekmanager.api.hello.dto.MessageDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldController {

    @GET
    @Path("/world")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {
        return Response.ok(new MessageDTO("Hello World!")).build();
    }
}
