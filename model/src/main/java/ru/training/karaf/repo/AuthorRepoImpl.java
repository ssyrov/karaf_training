package ru.training.karaf.repo;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import ru.training.karaf.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepoImpl implements AuthorRepo {

    private JpaTemplate template;

    public AuthorRepoImpl(JpaTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Author> getAll() {
        return template.txExpr(em -> em.createNamedQuery(AuthorDo.GET, AuthorDo.class).getResultList()).stream().map(AuthorImpl::new).collect(Collectors.toList());
    }

    @Override
    public void create(String name, int age, Collection<String> books) {
        AuthorDo authorToCreate = new AuthorDo();
        authorToCreate.setName(name);
        authorToCreate.setAge(age);

        template.tx(em -> {
            authorToCreate.setBooks(
                    em.createNamedQuery(BookDO.GET_ALL_WHERE_IN_AUTHORS, BookDO.class)
                    .setParameter("books", books)
                    .getResultList()
            );
            em.merge(authorToCreate);
        });
    }

    @Override
    public void delete(String name) {

    }
}

class AuthorImpl implements Author {

    private AuthorDo author;

    public AuthorImpl(AuthorDo author) {
        this.author = author;
    }

    @Override
    public String getName() {
        return author.getName();
    }

    @Override
    public int getAge() {
        return author.getAge();
    }

    @Override
    public Collection<String> getBooks() {
        return author.getBooks().stream().map(BookImpl::new).map(b -> b.getName()).collect(Collectors.toList());
    }

    @Override
    public <T> T unWrap(Class<T> clazz) {
        if (clazz == AuthorDo.class) {
            return (T) author;
        }
        return null;
        //throw new NoSuchFieldException("");
    }
}
