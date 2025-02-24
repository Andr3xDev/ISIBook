package edu.eci.cvds.reserves.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class User {
    @Document();
    @Id
    private int id;
    private String name;
    private String passwd;
    private String mail;
    private String type;
    private String status;
    private Date register;

    public User(String userName, String passwd) {
        this.name = userName;
        this.passwd = passwd;
    }
}
