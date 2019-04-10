package ru.training.karaf.rest.book.dto;

import ru.training.karaf.model.Book;

public class BookDTO {

    private String name;
    private String usr;

    public BookDTO() {}

    public BookDTO(Book book) {
        this.name = book.getName();
        this.usr = book.getUsr();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }


}
