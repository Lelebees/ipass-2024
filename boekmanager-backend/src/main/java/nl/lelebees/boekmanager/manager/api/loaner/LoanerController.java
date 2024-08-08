package nl.lelebees.boekmanager.manager.api.loaner;

import nl.lelebees.boekmanager.manager.api.loaner.dto.CreateLoanerDTO;
import nl.lelebees.boekmanager.manager.api.loaner.dto.LoanerDTO;
import nl.lelebees.boekmanager.manager.application.LoanerService;
import nl.lelebees.boekmanager.manager.domain.loaner.exception.LoanerNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

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
    @Produces(APPLICATION_JSON)
    public Response getLoanerById(@PathParam("loanerId") UUID loanerId) {
        try {
            return Response.ok(service.getLoaner(loanerId)).build();
        } catch (LoanerNotFoundException e) {
            return Response.status(NOT_FOUND).entity("Could not find Loaner with id: " + loanerId).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createLoaner(CreateLoanerDTO dto) {
        LoanerDTO loaner = service.createLoaner(dto);
        return Response.created(URI.create("./" + "/loaners/" + loaner.id())).entity(loaner).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllLoaners(@QueryParam("name") String name) {
        if (name != null) {
            return Response.ok(service.getLoanersByName(name)).build();
        }
        return Response.ok(service.getAllLoaners()).build();
    }
}
