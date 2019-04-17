package ru.training.karaf.repo;

import ru.training.karaf.model.RequestLog;

import java.util.List;

public interface RequestLogRepo {
    List<RequestLog> getAll();
    void add(String name, String author, int price);
}
