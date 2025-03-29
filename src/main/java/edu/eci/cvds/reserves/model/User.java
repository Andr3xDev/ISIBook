package edu.eci.cvds.reserves.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    private static final String ACTIVE = "ACTIVE";

    @Id
    private String id;
    private String name;
    private String password;
    private String username;
    private LocalDate register;
    private String type;
    private String status;
    private String mail;

    public User(String name, String username, String password, String type, String mail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.register = LocalDate.now();
        this.status = ACTIVE;
        this.type = type;
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + type.toUpperCase());
    }

    @Override
    public boolean isEnabled() {
        return status.equalsIgnoreCase(ACTIVE);
    }
}