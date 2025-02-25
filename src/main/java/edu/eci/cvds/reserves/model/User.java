package edu.eci.cvds.reserves.model;

import java.sql.Date; //Eliminate after complete the time bug

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
        this.register = new Date(id); // TODO: Set correct date
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

}
