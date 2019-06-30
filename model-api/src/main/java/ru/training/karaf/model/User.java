package ru.training.karaf.model;

import java.util.Collection;
import java.util.Set;

public interface User {
    String getFirstName();
    String getLastName();
    String getLogin();
    Integer getAge();
    String getAddress();
    Set<String> getProperties();
    String getPassword();
    boolean isAdmin();
    int getCountBooks();
    Collection<Book> getBooks();
}
