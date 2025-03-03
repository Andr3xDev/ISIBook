package edu.eci.cvds.reserves.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "reserves")
public class Reserve {
    @Id
    private String id;
    private String userId;
    private String classroomId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Reserve(String userId, String classroomId, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.classroomId = classroomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

