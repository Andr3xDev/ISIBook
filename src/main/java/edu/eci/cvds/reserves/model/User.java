package edu.eci.cvds.reserves.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    private static final String ACTIVE = "ACTIVE";

    @Id
    private String id;
    private String name;
    private String password;
    private String username;
    private LocalDate register;
    private String type;
    private String status;
    private String mail;

    public User(String name, String username, String password, String type, String mail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.register = LocalDate.now();
        this.status = ACTIVE;
        this.type = type;
        this.mail = mail;
    }

}