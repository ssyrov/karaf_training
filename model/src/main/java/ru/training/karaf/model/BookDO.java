package ru.training.karaf.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = BookDO.GET_ALL, query = "SELECT b FROM BookDO AS b"),
        @NamedQuery(name = BookDO.GET_BY_ID, query = "SELECT b FROM BookDO AS b WHERE b.id = :id"),
        @NamedQuery(name = BookDO.DELETE_BY_NAME, query = "DELETE FROM BookDO AS b WHERE b.name = :name"),
        @NamedQuery(name = BookDO.GET_ALL_BY_USR, query = "SELECT b FROM BookDO AS b WHERE b.usr = :usr")
})
public class BookDO {
    public static final String GET_ALL = "Books.getAll";
    public static final String GET_BY_ID = "Books.getById";
    public static final String DELETE_BY_NAME = "Books.deleteByName";
    public static final String GET_ALL_BY_USR = "Books.getAllByUsr";

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "usr")
    private String usr;

    public BookDO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    /*
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
    }*/
}
