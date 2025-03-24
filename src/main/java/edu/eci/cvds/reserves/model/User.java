package edu.eci.cvds.reserves.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    protected String name;
    protected String password;
    protected String username;
    protected LocalDate register;
    protected String type;
    protected String status;
    protected String mail;

    public User(String name, String username, String password, String type, String mail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.register = LocalDate.now();
        this.status = "Active";
        this.type = type;
        this.mail = mail;
    }

}