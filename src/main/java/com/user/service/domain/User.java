package com.user.service.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private Integer userId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Integer id,String login, String password) {
        this.userId = id;
        this.login = login;
        this.password = password;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDate = user.getBirthDate();
    }

    public enum UserFields {
       USER_ID("userId"),
        LOGIN("login"),
        PASSWORD("password"),
        FIRST_NAME("firstName"),
        LAST_NAME("lastName"),
        BIRTH_DATE("birthDate"),
        TOKEN("token");

        private final String value;
        UserFields(String s) {
            value=s;
        }
        public String getValue() {
            return value;
        }
    }
}
