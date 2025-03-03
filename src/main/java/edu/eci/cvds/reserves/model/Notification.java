package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "users")
public class Notification {

    @Id
    private String id;
    private String reserveID;
    private String userID;
    private LocalDateTime date;
    private String message;

    public Notification(String reserveID, String userID, LocalDateTime date, String message) {
        this.reserveID = reserveID;
        this.userID = userID;
        this.date = date;
        this.message = message;
    }
}