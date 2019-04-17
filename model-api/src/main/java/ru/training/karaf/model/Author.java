package ru.training.karaf.model;

import java.util.Collection;

public interface Author {
    String getName();
    int getAge();
    Collection<String> getBooks();
    <T> T unWrap(Class<T> clazz);
}
