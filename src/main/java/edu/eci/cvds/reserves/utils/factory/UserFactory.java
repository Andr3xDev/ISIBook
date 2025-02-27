package edu.eci.cvds.reserves.utils.factory;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import edu.eci.cvds.reserves.model.Admin;
import edu.eci.cvds.reserves.model.Teacher;
import edu.eci.cvds.reserves.model.User;

@Component
public class UserFactory {

    public User createUser(String type, Map<String, String> params) {
        Objects.requireNonNull(type, "El tipo de usuario no puede ser nulo");
        Objects.requireNonNull(params, "Los parámetros no pueden ser nulos");

        return switch (type.toLowerCase()) {
            case "teacher" -> new Teacher(
                    getRequiredParam(params, "name"),
                    getRequiredParam(params, "userName"),
                    getRequiredParam(params, "mail"),
                    getRequiredParam(params, "passwd"));
            case "admin" -> new Admin(
                    getRequiredParam(params, "name"),
                    getRequiredParam(params, "userName"),
                    getRequiredParam(params, "passwd"));
            default -> throw new IllegalArgumentException("Tipo de usuario no válido: " + type);
        };
    }

    private String getRequiredParam(Map<String, String> params, String key) {
        String value = params.get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El parámetro '" + key + "' es obligatorio y no puede estar vacío");
        }
        return value;
    }
}
