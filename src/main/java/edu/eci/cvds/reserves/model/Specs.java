package edu.eci.cvds.reserves.model;

import lombok.Data;

/**
 * Represents the specifications of a classroom.
 */
@Data
public class Specs {
    private String capacity;
    private String type;
    private String numComputers;
    private String pcTypes;
    private Boolean projector;
    private Boolean touchScreen;
    private String airConditioning;
}