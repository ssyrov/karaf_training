package ru.training.karaf.repo;

import ru.training.karaf.model.Author;
import ru.training.karaf.model.Book;
import ru.training.karaf.model.BookPrice;
import ru.training.karaf.model.Color;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepo {
    List<? extends Book> getAll();
    void create(String name, String usr, int price, int red, int green, int blue, int alpha, Collection<String> authors);
    Optional<? extends Book> get(long id);
    void delete(String name);
    List<? extends Book> getByUsr(String usr);
    void changePrice(String name, String author, int price);

    List<BookPrice> getPrices(long bookId);
}
