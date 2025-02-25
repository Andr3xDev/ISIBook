package edu.eci.cvds.reserves.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//TODO: Finish date attribute
//TODO: Contect BD

//@Document();
public class User {

    @Id
    private int id;
    private String name;
    private String passwd;
    private String mail;
    private String type;
    private String status;
    private Date register;

    public User(String userName, String passwd, String mail, String type) {
        this.name = userName;
        this.passwd = passwd;
        this.mail = mail;
        this.type = type;
        this.status = "Active";
    }
}
