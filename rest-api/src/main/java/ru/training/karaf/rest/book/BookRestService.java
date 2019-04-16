package ru.training.karaf.rest.book;

import ru.training.karaf.rest.book.dto.BookDTO;
import ru.training.karaf.rest.book.dto.BookDescriptionDTO;
import ru.training.karaf.rest.book.dto.BookPriceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookRestService {

    @GET
    List<BookDTO> getAll();

    @POST
    void create(BookDTO book);

    @GET
    @Path("{id}")
    BookDTO get(@PathParam("id") long id);

    @DELETE
    @Path("{name}")
    void delete(@PathParam("name") String name);

    @GET
    @Path("users/{usr}")
    List<BookDTO> getByUsr(@PathParam("usr") String usr);

    @POST
    @Path("{id}")
    void addDescription(@PathParam("id") long idBook, BookDescriptionDTO description);

    @GET
    @Path("{id}/history")
    List<BookPriceDTO> getPrices(@PathParam("id") long bookId);

}
