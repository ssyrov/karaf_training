package ru.training.karaf.rest.book.dto;

import ru.training.karaf.rest.author.dto.AuthorDTO;

import java.util.Collection;

public class BookDescriptionDTO {

    private long id;
    private String description;
    private String date;


    public BookDescriptionDTO() {
    }

    public BookDescriptionDTO(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public BookDescriptionDTO(long id, String description, Collection<AuthorDTO> authors, String date) {
        this.description = description;
        this.date = date;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
