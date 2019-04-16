package ru.training.karaf.repo;

import org.apache.aries.jpa.template.JpaTemplate;
import ru.training.karaf.model.*;

import java.util.Collection;

public class BookDescriptionRepoImpl implements BookDescriptionRepo {

    private JpaTemplate template;


    public BookDescriptionRepoImpl(JpaTemplate template) {
        this.template = template;
    }

    @Override
    public void create(long bookId, String description, String date) {
        BookDescriptionDo descriptionToCreate = new BookDescriptionDo();

        //AuthorDo author = template.txExpr(em -> em.createNamedQuery(AuthorDo.GET, AuthorDo.class).setParameter("name", authorName).getSingleResult());

        //descriptionToCreate.setAuthorDos(author);
        descriptionToCreate.setDescription(description);
        descriptionToCreate.setDate(date);

        BookDO bookDO = template.txExpr(em -> em.createNamedQuery(BookDO.GET_BY_ID, BookDO.class).setParameter("id", bookId).getSingleResult());
        descriptionToCreate.setBook(bookDO);

        bookDO.setDescriptionDo(descriptionToCreate);
        template.tx(em -> em.merge(bookDO));
        //template.tx(em -> em.persist(descriptionToCreate));

    }


    @Override
    public void delete() {

    }
}
class BookDescriptionImpl implements BookDescription {

    private BookDescriptionDo descriptionDo;

    BookDescriptionImpl(BookDescriptionDo descriptionDo) {
        if (descriptionDo == null) {
            this.descriptionDo = new BookDescriptionDo() {{
                this.setDescription("");
                this.setDate("");
                this.setId(-1);
            }
            };
        } else {
            this.descriptionDo = descriptionDo;
        }

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
}