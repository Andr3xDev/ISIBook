package edu.eci.cvds.reserves.model;

import java.time.LocalDateTime;

public abstract class User {

    protected String name;
    protected String password;
    protected String userName;
    protected LocalDateTime register;

    protected User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.register = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getRegister() {
        return register;
    }

}