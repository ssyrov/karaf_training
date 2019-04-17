package ru.training.karaf.repo;

import org.apache.aries.jpa.template.JpaTemplate;
import ru.training.karaf.model.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.Optional;

public class BookDescriptionRepoImpl implements BookDescriptionRepo {

    private JpaTemplate template;


    public BookDescriptionRepoImpl(JpaTemplate template) {
        this.template = template;
    }

    @Override
    public void create(long bookId, String description, String date) {
        BookDescriptionDo descriptionToCreate = new BookDescriptionDo();

        descriptionToCreate.setDescription(description);
        descriptionToCreate.setDate(date);

        template.tx(em -> {
            Optional<BookDO> bookDO = getById(bookId, em);
            if (bookDO.isPresent()) {
                descriptionToCreate.setBook(bookDO.get());

                bookDO.get().setDescriptionDo(descriptionToCreate);

                em.merge(bookDO.get());
            } else {
               //throw Exception? Book with that ID is not found?
            }

        });

    }

    private Optional<BookDO> getById(long bookId, EntityManager em) {
        try {
            return Optional.of(em.createNamedQuery(BookDO.GET_BY_ID, BookDO.class).setParameter("id", bookId).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public void delete() {

    }
}
class BookDescriptionImpl implements BookDescription {

    private BookDescriptionDo descriptionDo;

    BookDescriptionImpl(BookDescriptionDo descriptionDo) {
        this.descriptionDo = descriptionDo;
    }

    @Override
    public long getId() {
        return descriptionDo.getId();
    }

    @Override
    public String getDescription() {
        return descriptionDo.getDescription();
    }


    @Override
    public String getDate() {
        return descriptionDo.getDate();
    }

    @Override
    public boolean isNull() {
        return descriptionDo == null;
    }
}