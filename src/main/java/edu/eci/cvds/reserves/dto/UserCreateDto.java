package edu.eci.cvds.reserves.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {

    private String name;
    private String password;
    private String username;
    private String type;
    private String mail;

}