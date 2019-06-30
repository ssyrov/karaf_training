package ru.training.karaf.rest.user.dto;

import ru.training.karaf.model.UserAuth;

public class UserAuthDTO {

    private String login;
    private String password;

    public UserAuthDTO() {
    }

    public UserAuthDTO(UserAuth user) {
        login = user.getLogin();
        password = user.getPassword();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
