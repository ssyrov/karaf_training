package ru.training.karaf.repo;

import ru.training.karaf.model.Author;

import java.util.Collection;

public interface BookDescriptionRepo {
    void create(long idBook, String description, String date);
    void delete();//?
}
