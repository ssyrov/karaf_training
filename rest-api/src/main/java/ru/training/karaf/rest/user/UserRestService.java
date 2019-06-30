package ru.training.karaf.rest.user;

import ru.training.karaf.model.Book;
import ru.training.karaf.rest.user.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserRestService {
    
    @GET
    List<UserDTO> getAll();
    
    @POST
    void create(UserDTO user);

    @GET
    @Path("{login}")
    UserDTO get(@PathParam("login") String login);
    
    @DELETE
    @Path("{login}")
    void delete(@PathParam("login") String login);

    @GET
    @Path("{login}/books")
    Collection<Book> getBooks(@PathParam("login") String login);
}
