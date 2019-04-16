package ru.training.karaf.rest.book.dto;

import ru.training.karaf.model.BookPrice;


public class BookPriceDTO {
    private String date;
    private int price;

    public BookPriceDTO() {
    }

    public BookPriceDTO(BookPrice bookPrice) {
        String format = String.format("%d-%d-%d %d:%d:%d",
                bookPrice.getDate().getYear(),
                bookPrice.getDate().getMonthValue(),
                bookPrice.getDate().getDayOfMonth(),
                bookPrice.getDate().getHour(),
                bookPrice.getDate().getMinute(),
                bookPrice.getDate().getSecond()
        );
        this.date = format;
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
