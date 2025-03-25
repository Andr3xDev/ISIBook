package edu.eci.cvds.reserves.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String username;
    private LocalDate register;
    private String type;
    private String status;
    private String mail;
}