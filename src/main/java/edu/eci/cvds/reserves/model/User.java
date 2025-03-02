package edu.eci.cvds.reserves.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
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

}