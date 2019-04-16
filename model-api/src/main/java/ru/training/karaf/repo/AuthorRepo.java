package ru.training.karaf.repo;

import ru.training.karaf.model.Author;
import ru.training.karaf.model.Book;

import java.util.Collection;

public interface AuthorRepo {
    Collection<Author> getAll();
    void create(String name, int age, Collection<String> books);
    void delete(String name);
}
