package ru.training.karaf.repo;

import org.apache.aries.jpa.template.JpaTemplate;
import ru.training.karaf.model.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;
import java.util.stream.Collectors;

public class BookRepoImpl implements BookRepo {
    private JpaTemplate template;

    public BookRepoImpl(JpaTemplate template) {
        this.template = template;
    }

    @Override
    public List<Book> getAll() {
        return template.txExpr(em -> em.createNamedQuery(BookDO.GET_ALL, BookDO.class).getResultList())
                .stream()
                .map(BookImpl::new)
                .collect(Collectors.toList());

    }

    @Override
    public void create(String name, String usr, int price, int red, int green, int blue, int alpha, Collection<String> authors) {
        BookDO bookToCreate = new BookDO();
        ColorImpl color = new ColorImpl(red, green, blue, alpha);
        bookToCreate.setName(name);
        bookToCreate.setColor(color);


        /*BookPriceRepoImpl bookPriceRepo = new BookPriceRepoImpl(template);
        long l = bookPriceRepo.create(price);
        bookToCreate.getPrice().add(template.txExpr(em -> em.find(BookPriceDO.class, l)));*/

        bookToCreate.setAuthors(template.txExpr(em -> em.createNamedQuery(AuthorDo.GET_WHERE_IN, AuthorDo.class).setParameter("authors", authors)
                .getResultList()));

        UserDO userDO = template.txExpr(em -> em.createNamedQuery(UserDO.GET_BY_LOGIN, UserDO.class).setParameter("login", usr).getSingleResult());
        bookToCreate.setUsr(userDO);

        template.tx(em -> {
            BookPriceRepoImpl bookPriceRepo = new BookPriceRepoImpl(template);
            bookPriceRepo.create(price, new BookImpl(em.merge(bookToCreate)));
        });


    }


    @Override
    public Optional<Book> get(long id) {
        return template.txExpr(em -> getById(id, em)).map(BookImpl::new);
    }

    @Override
    public void delete(String name) {
        template.tx(em -> em.createNamedQuery(BookDO.DELETE_BY_NAME, BookDO.class).setParameter("name", name).executeUpdate());
    }

    @Override
    public List<Book> getByUsr(String usr) {
        return template.txExpr(em -> em.createNamedQuery(BookDO.GET_ALL_BY_USR, BookDO.class).setParameter("usr", usr).getResultList())
                .stream()
                .map(BookImpl::new)
                .collect(Collectors.toList());
    }

    private Optional<BookDO> getById(long id, EntityManager em) {
        try {
            return Optional.of(em.createNamedQuery(BookDO.GET_BY_ID, BookDO.class).setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /*@Override
    public void changePrice(String name, String author, int price) {
        //Optional<BookDO> bookDO = template.txExpr(em -> getByNameAndAuthor(em, name, author));
        //if (bookDO.isPresent()) {
            //BookDO book = bookDO.get();
            BookPriceRepoImpl bookPriceRepo = new BookPriceRepoImpl(template);
            bookPriceRepo.create(price, book.getId());
        //} else {
            //RequestLogRepoImpl requestLogRepo = new RequestLogRepoImpl(template);
            //requestLogRepo.add(name, author, price);
        //}

    }*/

    @Override
    public List<BookPrice> getPrices(long bookId) {
        BookPriceRepoImpl repo = new BookPriceRepoImpl(template);
        return repo.getByBook(bookId);
    }

    @Override
    public Optional<Book> getByNameAndAuthor(String name, String author) {
        try {
            Optional<BookDO> bookDO = Optional.of(template.txExpr(em -> em.createNamedQuery(BookDO.GET_BY_NAME_AND_AUTHOR, BookDO.class)
                    .setParameter("name", name)
                    .setParameter("author", author)
                    .getSingleResult()));
            return bookDO.map(BookImpl::new);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}

class BookImpl implements Book {

    private final BookDO bookDO;

    BookImpl(BookDO bookDO) {
        this.bookDO = bookDO;
    }

    BookDO getDO(){
        return bookDO;
    }

    @Override
    public long getId() {
        return bookDO.getId();
    }

    @Override
    public String getName() {
        return bookDO.getName();
    }

    @Override
    public String getUsr() {
        return bookDO.getUsr().getLogin();
    }

    @Override
    public BookDescription getBookDescription() {
        return new BookDescriptionImpl(bookDO.getDescriptionDo());
    }

    @Override
    public Color getColor() {
        return bookDO.getColor();
    }

    @Override
    public Collection<String> getAuthorNames() {
        return bookDO.getAuthors().stream().map(AuthorImpl::new).map(AuthorImpl::getName).collect(Collectors.toList());
    }

    @Override
    public Collection<Author> getAuthors() {
        return bookDO.getAuthors().stream().map(AuthorImpl::new).collect(Collectors.toList());
    }
    @Override
    public <T> T unWrap(Class<T> clazz) {
        if (clazz == BookDO.class) {
            return (T) bookDO;
        }
        return null;
        //throw new NoSuchFieldException("");
    }

    @Override
    public int getPrice() {

        try {
            Optional<BookPriceDO> bookPriceDO = bookDO.getPrice().stream().max(Comparator.comparing(BookPriceDO::getDate));
            return bookPriceDO.get().getPrice();
        } catch (NoResultException e) {
            return 0;

        }
    }
}
