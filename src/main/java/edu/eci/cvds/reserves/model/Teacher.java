package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class Teacher extends User {

    @Id
    private String id;
    private String mail;
    private String status;

    public Teacher(String name, String userName, String mail, String passwd) {
        super(name, userName, passwd);
        this.mail = mail;
        this.status = "Active";
        this.type = "Teacher";
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}