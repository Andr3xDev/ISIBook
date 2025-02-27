package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class Admin extends User {

    @Id
    private int id;

    public Admin(String name, String userName, String passwd) {
        super(name, userName, passwd);
    }
}