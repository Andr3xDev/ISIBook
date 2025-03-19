package edu.eci.cvds.reserves.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public abstract class User {

    protected String name;
    protected String password;
    protected String username;
    protected LocalDateTime register;
    @Field("type_user")
    protected String type;

    protected User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.register = LocalDateTime.now();
    }

}