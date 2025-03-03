package edu.eci.cvds.reserves.model;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String purpose;  // <-- Se cambió de 'Text' a 'String'
    private String repetitiveTime;
}

