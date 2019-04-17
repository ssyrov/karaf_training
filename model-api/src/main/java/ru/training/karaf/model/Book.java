package ru.training.karaf.model;

import java.util.Collection;

public interface Book {
    long getId();
    String getName();
    String getUsr();
    BookDescription getBookDescription();
    Color getColor();
    Collection<String> getAuthorNames();
    Collection<Author> getAuthors();
    <T> T unWrap(Class<T> clazz);
    int getPrice();
}
