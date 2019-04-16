package ru.training.karaf.repo;

import ru.training.karaf.model.Book;
import ru.training.karaf.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepo {
    List<? extends User> getAll();
    void create(String login, String firstName, String lastName, String address, Integer age, Set<String> properties, String password, boolean admin, int count);
    Optional<? extends User> get(String login);
    void delete(String login);
    void updateCount(String usr);
}
