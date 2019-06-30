package ru.training.karaf.rest.book.dto;

import ru.training.karaf.model.Author;
import ru.training.karaf.model.Book;
import ru.training.karaf.model.BookDescription;
import ru.training.karaf.model.Color;

import java.util.Collection;

public class BookDTO {

    private long id;
    private String name;
    private String usr;
    private BookDescription description;
    private Collection<String> authors;
    private int price;

    private ColorDTO color;

    public BookDTO() {}

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.usr = book.getUsr();
        this.description = book.getBookDescription();
        this.color = new ColorDTO(book.getColor().getRed(), book.getColor().getGreen(), book.getColor().getBlue(), book.getColor().getAlpha());
        this.authors = book.getAuthorNames();
        this.price = book.getPrice();
    }

    public BookDescription getDescription() {
        if (description == null) {
            return new BookDescription() {
                @Override
                public long getId() {
                    return -1;
                }

                @Override
                public String getDescription() {
                    return "";
                }

                @Override
                public String getDate() {
                    return "";
                }
            };
        } else {

            return description;
        }
    }

    public Collection<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<String> authors) {
        this.authors = authors;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public void setDescription(BookDescription description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ColorDTO getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
