package edu.eci.cvds.reserves.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Schedule")
public class Schedule {

}