package ru.training.karaf.rest.requestLog.dto;

import ru.training.karaf.model.RequestLog;

public class RequestLogDTO {
    private String name;
    private String author;
    private int price;

    public RequestLogDTO(RequestLog requestLog) {
        this.name = requestLog.getName();
        this.author = requestLog.getAuthor();
        this.price = requestLog.getPrice();
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
