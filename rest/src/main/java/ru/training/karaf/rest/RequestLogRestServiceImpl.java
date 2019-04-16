package ru.training.karaf.rest;

import ru.training.karaf.model.RequestLog;
import ru.training.karaf.repo.RequestLogRepo;
import ru.training.karaf.rest.requestLog.RequestLogRestService;
import ru.training.karaf.rest.requestLog.dto.RequestLogDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RequestLogRestServiceImpl implements RequestLogRestService {

    private RequestLogRepo repo;

    public void setRepo(RequestLogRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<RequestLogDTO> getAll() {
        return repo.getAll().stream().map(RequestLogDTO::new).collect(Collectors.toList());
    }
}
