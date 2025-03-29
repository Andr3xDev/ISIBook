package edu.eci.cvds.reserves.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for creating a new user.
 */
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