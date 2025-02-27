package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teachers")
public class Teacher extends User {

    @Id
    private int id;
    private String mail;
    private String status;

    public Teacher(String name, String userName, String mail, String passwd) {
        super(name, userName, passwd);
        this.mail = mail;
        this.status = "Active";
    }

    public String getMail() {
        return mail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}