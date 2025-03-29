package edu.eci.cvds.reserves.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for User.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String username;
    private LocalDate register;
    private String type;
    private String status;
    private String mail;

}