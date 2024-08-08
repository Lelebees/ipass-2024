package nl.lelebees.boekmanager.manager.api.book;

import nl.lelebees.boekmanager.manager.api.book.dto.BookDTO;
import nl.lelebees.boekmanager.manager.api.book.dto.CreateBookDTO;
import nl.lelebees.boekmanager.manager.application.BookService;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;
import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/books")
@RolesAllowed({"admin"})
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    public BookController() {
        this.service = new BookService();
    }

    @GET
    @Path("/{bookId}")
    @Produces(APPLICATION_JSON)
    public Response getBookByID(@PathParam("bookId") UUID bookId) {
        try {
            return Response.ok(service.getBook(bookId)).build();
        } catch (BookNotFoundException e) {
            return Response.status(NOT_FOUND).entity("Could not find Book with id: " + bookId).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createBook(CreateBookDTO bookDTO) {
        try {
            BookDTO book = service.createBook(bookDTO);
            return Response.created(URI.create("./" + "/books/" + book.id())).entity(book).build();
        } catch (NoTitleEnteredException e) {
            return Response.status(BAD_REQUEST).entity("The Book title is null!").build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllBooks(@QueryParam("author") String authorQuery) {
        if (authorQuery != null) {
            return Response.ok(service.getBooksByAuthor(authorQuery)).build();
        }
        return Response.ok(service.getAllBooks()).build();
    }

}
