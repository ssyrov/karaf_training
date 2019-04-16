package ru.training.karaf.model;

import ru.training.karaf.converter.DateConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = BookPriceDO.GET_ALL, query = "SELECT p FROM BookPriceDO AS p"),
        @NamedQuery(name = BookPriceDO.GET_ALL_BY_BOOK, query = "SELECT p FROM BookPriceDO AS p WHERE p.book.id = :bookid")
})
public class BookPriceDO {

    public static final String GET_ALL = "BookPriceDo.getAll";
    public static final String GET_ALL_BY_BOOK = "BookPriceDo.getAllByBook";
    @Id
    @GeneratedValue
    private long id;
    private int price;
    @Convert(converter = DateConverter.class)
    private LocalDateTime date;
   //private int date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private BookDO book;

    public BookPriceDO() {
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BookDO getBook() {
        return book;
    }

    public void setBook(BookDO book) {
        this.book = book;
    }
}
