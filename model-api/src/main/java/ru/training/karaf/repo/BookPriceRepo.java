package ru.training.karaf.repo;

import ru.training.karaf.model.Book;
import ru.training.karaf.model.BookPrice;

import java.sql.Timestamp;
import java.util.List;

public interface BookPriceRepo {
    List<? extends BookPrice> getAll();
    void create(int price, Book book);
    List<? extends BookPrice> getByBook(long bookId);
}
