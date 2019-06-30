package ru.training.karaf.rest;

import ru.training.karaf.model.Author;
import ru.training.karaf.repo.AuthorRepo;
import ru.training.karaf.rest.author.AuthorRestService;
import ru.training.karaf.rest.author.dto.AuthorDTO;

import java.util.Collection;

public class AuthorRestServiceImpl implements AuthorRestService {

    private AuthorRepo repo;

    public void setRepo(AuthorRepo repo) {
        this.repo = repo;
    }

    @Override
    public Collection<Author> getAll() {
        return repo.getAll();
    }

    @Override
    public void create(AuthorDTO author) {
        repo.create(author.getName(), author.getAge(), author.getBooks());
    }
}
