package edu.eci.cvds.reserves.dto;

import lombok.Data;

@Data
public class UserCreateDto {

    private String name;
    private String password;
    private String username;
    private String type;
    private String mail;

}