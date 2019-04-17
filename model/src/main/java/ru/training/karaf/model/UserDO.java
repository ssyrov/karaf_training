package ru.training.karaf.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@NamedQueries({
    @NamedQuery(name = UserDO.GET_ALL, query = "SELECT u FROM UserDO AS u"),
    @NamedQuery(name = UserDO.GET_BY_LOGIN, query = "SELECT u FROM UserDO AS u WHERE u.login = :login"),
    @NamedQuery(name = UserDO.UPDATE_COUNT_BY_LOGIN, query = "UPDATE UserDO u SET u.countBooks=:count WHERE u.login=:login"),

})
public class UserDO {
    public static final String GET_ALL = "Users.getAll";
    public static final String GET_BY_LOGIN = "Users.getByLogin";
    public static final String UPDATE_COUNT_BY_LOGIN = "Users.updateCountByLogin";

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login", nullable = false, unique=true)
    private String login;
    private Integer age;
    private String address;
    @Column(name = "pswd")
    private String password;
    @Column(name = "adm")
    private boolean admin;

    @OneToMany(mappedBy = "usr", fetch = FetchType.EAGER)
    private Collection<BookDO> books;

    @Column(name = "count_books")
    private int countBooks;

    @ElementCollection
    @CollectionTable(name = "user_properties",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<String> properties;

    public UserDO() {}


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Set<String> getProperties() {
        return properties;
    }
    public void setProperties(Set<String> properties) {
        this.properties = properties;
    }

    public int getCountBooks() {
        return countBooks;
    }

    public void setCountBooks(int countBooks) {
        this.countBooks = countBooks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Collection<BookDO> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookDO> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDO other = (UserDO) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (properties == null) {
            return other.properties == null;
        } else return properties.equals(other.properties);
    }
    @Override
    public String toString() {
        return "UserDO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", login=" + login
                + ", age=" + age + ", address=" + address + ", properties=" + properties + "]";
    }
}
