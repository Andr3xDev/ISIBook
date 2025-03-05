package edu.eci.cvds.reserves.model;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "reserves")
public class Reserve{
    @Id
    private String id;
    private String userId;
    private String classroomId;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String status;
    private boolean repetitive;
    private String purpose;
    private String repetitiveTime;
    public Reserve(String userId,String classroomId,LocalDateTime startDate,LocalDateTime finishDate,String status,boolean repetitive,String purpose,String repetitiveTime){
        this.userId = userId;
        this.classroomId = classroomId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
        this.repetitive = repetitive;
        this.purpose = purpose;
        this.repetitiveTime = repetitiveTime;
    }
}

