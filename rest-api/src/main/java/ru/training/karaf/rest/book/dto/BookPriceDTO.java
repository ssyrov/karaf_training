package ru.training.karaf.rest.book.dto;

import ru.training.karaf.model.BookPrice;

import java.time.format.DateTimeFormatter;


public class BookPriceDTO {
    private String date;
    private int price;

    public BookPriceDTO() {
    }

    public BookPriceDTO(BookPrice bookPrice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        this.date = formatter.format(bookPrice.getDate());
        this.price = bookPrice.getPrice();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
