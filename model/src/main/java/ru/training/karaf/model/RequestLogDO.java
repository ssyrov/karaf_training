package ru.training.karaf.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = RequestLogDO.GET_ALL, query = "SELECT r FROM RequestLogDO AS r")
})
public class RequestLogDO {
    public static final String GET_ALL = "RequestLogDo.getAll";
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String author;
    private int price;

    public RequestLogDO() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
