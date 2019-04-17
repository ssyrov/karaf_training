package ru.training.karaf.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = AuthorDo.GET, query = "SELECT d FROM AuthorDo AS d"),
        @NamedQuery(name = AuthorDo.GET_WHERE_IN, query = "SELECT a FROM AuthorDo AS a WHERE a.name IN :authors"),
        //@NamedQuery(name = AuthorDo.GET_ALL_BY_NAME, query = "SELECT d FROM AuthorDo WHERE d.name = :name")
})
public class AuthorDo {
    public static final String GET = "AuthorDo.get";
    public static final String GET_WHERE_IN = "AuthorDo.getWhereIn";
    //public static final String GET_ALL = "Author.getAll";
    //public static final String GET_ALL_BY_NAME = "Author.getAllByName";

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Collection<BookDO> books;

    public AuthorDo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Collection<BookDO> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookDO> books) {
        this.books = books;
    }
}
