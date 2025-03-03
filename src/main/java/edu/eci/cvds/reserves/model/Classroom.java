package edu.eci.cvds.reserves.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "classrooms")
public class Classroom {

    @Id
    private String id;
    private String name;
    private String type;
    private Specs specs;
    private Schedule schedule;

    public Classroom(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}