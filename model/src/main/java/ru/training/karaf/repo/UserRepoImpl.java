package ru.training.karaf.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.aries.jpa.template.JpaTemplate;

import ru.training.karaf.model.Book;
import ru.training.karaf.model.BookDO;
import ru.training.karaf.model.User;
import ru.training.karaf.model.UserDO;

public class UserRepoImpl implements UserRepo {
    private JpaTemplate template;

    public UserRepoImpl(JpaTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> getAll() {
        return template.txExpr(em -> em.createNamedQuery(UserDO.GET_ALL, UserDO.class).getResultList())
                .stream()
                .map(UserImpl::new)
                .collect(Collectors.toList());//2
    }

    @Override
    public void create(String login, String firstName, String lastName, String address, Integer age, Set<String> properties, String password, boolean admin, int count) {
        UserDO userToCreate = new UserDO();
        userToCreate.setLogin(login);
        userToCreate.setFirstName(firstName);
        userToCreate.setLastName(lastName);
        userToCreate.setAddress(address);
        userToCreate.setAge(age);
        userToCreate.setProperties(properties);
        userToCreate.setAdmin(admin);
        userToCreate.setPassword(password);
        userToCreate.setCountBooks(count);
        template.tx(em -> em.persist(userToCreate));
    }

    @Override
    public Optional<User> get(String login) {
        return template.txExpr(em -> getByLogin(login, em)).map(UserImpl::new);
    }

    @Override
    public void delete(String login) {
        template.tx(em -> getByLogin(login, em).ifPresent(em::remove));
    }

    @Override
    public void updateCount(String usr) {
        template.tx(em -> getByLogin(usr, em).ifPresent(udo -> {
                udo.setCountBooks(udo.getCountBooks()+1);
                em.merge(udo);
            })
        );
    }




    private Optional<UserDO> getByLogin(String login, EntityManager em) {
        try {
            return Optional.of(em.createNamedQuery(UserDO.GET_BY_LOGIN, UserDO.class).setParameter("login", login)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}

class UserImpl implements User{

    private final UserDO userDO;

    UserImpl(UserDO userDO) {
        this.userDO = userDO;
    }


    @Override
    public String getFirstName() {
        return userDO.getFirstName();
    }

    @Override
    public String getLastName() {
        return userDO.getLastName();
    }

    @Override
    public String getLogin() {
        return userDO.getLogin();
    }

    @Override
    public Integer getAge() {
        return userDO.getAge();
    }

    @Override
    public String getAddress() {
        return userDO.getAddress();
    }

    @Override
    public Set<String> getProperties() {
        return userDO.getProperties();
    }

    @Override
    public String getPassword() {
        return userDO.getPassword();
    }

    @Override
    public boolean isAdmin() {
        return userDO.isAdmin();
    }

    @Override
    public int getCountBooks() {
        return userDO.getCountBooks();
    }

    @Override
    public Collection<Book> getBooks() {
        return userDO.getBooks().stream().map(BookImpl::new).collect(Collectors.toList()); //?
    }


    UserDO getDO(){
        return userDO;
    }
}
