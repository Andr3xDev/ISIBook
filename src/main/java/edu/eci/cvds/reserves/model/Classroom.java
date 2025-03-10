package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "classrooms")
@Data
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Classroom {

    @Id
    private String id;
    private String name;
    private String build;
    private String type;
    private Specs specs;
    private Schedule schedule;

}