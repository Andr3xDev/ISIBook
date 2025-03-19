package edu.eci.cvds.reserves.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public abstract class User {

    protected String name;
    protected String password;
    protected String username;
    protected LocalDate register;
    protected String type;
    protected String staus;
    private String mail;

    protected User(String name, String username, String password, String type, String mail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.register = LocalDate.now();
        this.staus = "Active";
        this.type = type;
        this.mail = mail;
    }

}