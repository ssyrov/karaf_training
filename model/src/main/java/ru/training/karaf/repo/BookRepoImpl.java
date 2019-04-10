package ru.training.karaf.repo;

import org.apache.aries.jpa.template.JpaTemplate;
import ru.training.karaf.model.Book;
import ru.training.karaf.model.BookDO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
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
    public void create(String name, String usr) {
        BookDO bookToCreate = new BookDO();
        bookToCreate.setName(name);
        bookToCreate.setUsr(usr);
        template.tx(em -> em.persist(bookToCreate));
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
    public String getName() {
        return bookDO.getName();
    }

    @Override
    public String getUsr() {
        return bookDO.getUsr();
    }
}
