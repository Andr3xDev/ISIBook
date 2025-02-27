package edu.eci.cvds.reserves.utils.factory;

import org.springframework.stereotype.Component;

import edu.eci.cvds.reserves.model.Admin;
import edu.eci.cvds.reserves.model.Teacher;
import edu.eci.cvds.reserves.model.User;

import java.util.Map;

@Component
public class UserFactory {

    public User createUser(String type, Map<String, String> params) {
        return switch (type.toLowerCase()) {
            case "teacher" -> new Teacher(
                    params.get("name"),
                    params.get("userName"),
                    params.get("mail"),
                    params.get("passwd"));
            case "admin" -> new Admin(
                    params.get("name"),
                    params.get("userName"),
                    params.get("passwd"));
            default -> throw new IllegalArgumentException("Tipo de usuario no válido");
        };
    }
}
