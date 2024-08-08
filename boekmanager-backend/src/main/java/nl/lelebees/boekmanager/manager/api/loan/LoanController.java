package nl.lelebees.boekmanager.manager.api.loan;

import nl.lelebees.boekmanager.Main;
import nl.lelebees.boekmanager.manager.api.loan.dto.CreateLoanDTO;
import nl.lelebees.boekmanager.manager.api.loan.dto.LoanDTO;
import nl.lelebees.boekmanager.manager.application.LoanService;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookAlreadyLoanedException;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;
import nl.lelebees.boekmanager.manager.domain.loan.exception.LoanNotFoundException;
import nl.lelebees.boekmanager.manager.domain.loaner.exception.LoanerNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/loans")
public class LoanController {
    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    public LoanController() {
        this(new LoanService());
    }

    @GET
    @Path("/{loanId}")
    @Produces(APPLICATION_JSON)
    public Response getLoan(@PathParam("loanId") UUID loanId) {
        try {
            return Response.ok(service.getLoan(loanId)).build();
        } catch (LoanNotFoundException e) {
            return Response.status(NOT_FOUND).entity("Could not find Loan with id:" + loanId).build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getLoans() {
        Main.logger.info("Received request for all Loans!");
        return Response.ok(service.getAllLoans()).build();
    }


//    @GET
//    @Produces(APPLICATION_JSON)
//    public Response getLoans(@QueryParam("loaner") UUID loanerId, @QueryParam("book") UUID bookId) {
//        System.out.println(loanerId);
//        System.out.println(bookId);
//        if (loanerId != null) {
//            try {
//                return Response.ok(service.getLoansByLoaner(loanerId)).build();
//            } catch (LoanerNotFoundException e) {
//                return Response.status(NOT_FOUND).entity("Could not find Loans for Loaner with id:" + loanerId + ". Loaner could not be found.").build();
//            }
//        }
//        if (bookId != null) {
//            try {
//                return Response.ok(service.getLoanByBook(bookId)).build();
//            } catch (BookNotFoundException e) {
//                return Response.status(NOT_FOUND).entity("Could not find Loan for Book with id:" + bookId + ". Book could not be found.").build();
//            } catch (LoanNotFoundException e) {
//                return Response.status(NOT_FOUND).entity("Could not find Loan for Book with id:" + bookId + ". The Book has no Loan associated.").build();
//            }
//        }
//        return Response.ok(service.getAllLoans()).build();
//    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createLoan(CreateLoanDTO dto) {
        try {
            LoanDTO loan = service.createLoan(dto);
            return Response.created(URI.create("./" + "/loans/" + loan.id())).entity(loan).build();
        } catch (BookNotFoundException e) {
            return Response.status(NOT_FOUND).entity("Could not create Loan. Book with id: " + dto.bookId() + " could not be found.").build();
        } catch (LoanerNotFoundException e) {
            return Response.status(NOT_FOUND).entity("Could not create Loan. Loaner with id: " + dto.loanerId() + " could not be found.").build();
        } catch (BookAlreadyLoanedException e) {
            return Response.status(CONFLICT).entity("Could not create Loan. The book with id:" + dto.bookId() + " is already loaned.").build();
        }
    }

    @DELETE
    @Produces(TEXT_PLAIN)
    @Path("/{loanId}")
    public Response deleteLoan(@PathParam("loanId") UUID loanId) {
        service.deleteLoan(loanId);
        return Response.ok("Loan with id:" + loanId + " deleted successfully").build();
    }
}
