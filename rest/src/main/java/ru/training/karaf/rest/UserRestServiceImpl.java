package ru.training.karaf.rest;

import ru.training.karaf.repo.UserRepo;
import ru.training.karaf.rest.user.UserRestService;
import ru.training.karaf.rest.user.dto.UserDTO;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class UserRestServiceImpl implements UserRestService {

    private UserRepo repo;
    
    public void setRepo(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<UserDTO> getAll() {//1
        return repo.getAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public void create(UserDTO user) {
        repo.create(user.getLogin(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getAge(), user.getProperties(), user.getPassword(), user.isAdmin(), user.getCount());
    }

    @Override
    public UserDTO get(String login) {
        return repo.get(login).map(UserDTO::new)
                .orElseThrow(() -> new NotFoundException(Response.status(Response.Status.NOT_FOUND)
                        .type(MediaType.TEXT_HTML).entity("User not found").build()));
    }

    @Override
    public void delete(String login) {
        repo.delete(login);
    }
}
