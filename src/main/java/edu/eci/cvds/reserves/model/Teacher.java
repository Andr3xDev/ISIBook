package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Teacher extends User {

    @Id
    private int id;
    private String mail;
    private String status;
    private String type;

    public Teacher(String name, String userName, String mail, String passwd) {
        super(name, userName, passwd);
        this.mail = mail;
        this.status = "Active";
        this.type = "Teacher";
    }

    public String getMail() {
        return mail;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}