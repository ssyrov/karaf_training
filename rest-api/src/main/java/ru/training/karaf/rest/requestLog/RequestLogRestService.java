package ru.training.karaf.rest.requestLog;

import ru.training.karaf.rest.requestLog.dto.RequestLogDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("log")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RequestLogRestService {

    @GET
    List<RequestLogDTO> getAll();
}
