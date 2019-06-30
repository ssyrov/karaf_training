package ru.training.karaf.rest;

import ru.training.karaf.repo.UserRepo;
import ru.training.karaf.rest.auth.AuthRestService;
import ru.training.karaf.rest.user.dto.UserAuthDTO;
import ru.training.karaf.rest.user.dto.UserDTO;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AuthRestServiceImpl implements AuthRestService {

    private UserRepo repo;

    public void setRepo(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDTO auth(UserAuthDTO userAuth) {
        return repo.get(userAuth.getLogin()).map(UserDTO::new).filter(udo -> udo.getPassword().equals(userAuth.getPassword()))
                .orElseThrow(() -> new NotFoundException(Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_HTML).entity("User not found").build()));
        /*TestShiro test = new TestShiro();
        test.login(userAuth.getLogin(), userAuth.getPassword(), repo);*/
    }

}
