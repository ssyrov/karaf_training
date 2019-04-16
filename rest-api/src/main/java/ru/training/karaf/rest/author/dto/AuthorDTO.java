package ru.training.karaf.rest.author.dto;

import ru.training.karaf.model.Book;
import ru.training.karaf.model.BookDescription;

import java.util.Collection;

public class AuthorDTO {
    private String name;
    private int age;
    private Collection<String> books;

    AuthorDTO() {

    }

    public AuthorDTO(String name, int age, Collection<String> books) {
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Collection<String> getBooks() {
        return books;
    }

    public void setBooks(Collection<String> books) {
        this.books = books;
    }
}
