package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class Admin extends User {

    @Id
    private int id;
    private String type;

    public Admin(String name, String userName, String passwd) {
        super(name, userName, passwd);
        this.type = "Admin";
    }

}