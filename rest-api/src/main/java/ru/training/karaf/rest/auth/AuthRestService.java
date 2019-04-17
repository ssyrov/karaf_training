package ru.training.karaf.rest.auth;

import ru.training.karaf.rest.user.dto.UserAuthDTO;
import ru.training.karaf.rest.user.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthRestService {

    @POST
    UserDTO auth(UserAuthDTO userAuth);

}
