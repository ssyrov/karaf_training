package ru.training.karaf.repo;

import ru.training.karaf.model.Book;
import ru.training.karaf.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepo {
    List<? extends Book> getAll();
    void create(String name, String usr);
    Optional<? extends Book> get(long id);
    void delete(String name);
    List<? extends Book> getByUsr(String usr);
}
