package nl.lelebees.boekmanager.manager.api.loaner;

import nl.lelebees.boekmanager.manager.application.LoanerService;
import nl.lelebees.boekmanager.manager.domain.loaner.exception.LoanerNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/loaners")
public class LoanerController {
    private final LoanerService service;


    public LoanerController(LoanerService service) {
        this.service = service;
    }

    public LoanerController() {
        this(new LoanerService());
    }

    @GET
    @Path("/{loanerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoanerById(@PathParam("loanerId") UUID loanerId) {
        try {
            return Response.ok(service.getLoaner(loanerId)).build();
        } catch (LoanerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not find Loaner with id: " + loanerId).build();
        }
    }
}
