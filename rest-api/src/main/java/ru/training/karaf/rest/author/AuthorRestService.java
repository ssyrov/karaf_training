package ru.training.karaf.rest.author;


import ru.training.karaf.model.Author;
import ru.training.karaf.rest.author.dto.AuthorDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthorRestService {

    @GET
    Collection<Author> getAll();

    @POST
    void create(AuthorDTO author);


}
