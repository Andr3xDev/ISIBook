package edu.eci.cvds.reserves.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.reserves.utils.factory.UserFactory;

class UtilsUserTest {

    UserFactory userFactory;

    @BeforeEach
    void setUp() {
        this.userFactory = new UserFactory();
    }

    @Test
    void shouldCreateAdmin() {
        assertDoesNotThrow(() -> {
            Map<String, String> adminParams = Map.of(
                    "name", "Super Admin",
                    "userName", "adminUser",
                    "passwd", "adminPass");

            userFactory.createUser("Admin", adminParams);
        });
    }

    @Test
    void shouldNotCreateAdmin() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> adminParams = Map.of(
                    "name", "Super Admin",
                    "userName", "adminUser");

            userFactory.createUser("Admin", adminParams);
        });
    }

    @Test
    void shouldCreateTeache() {
        assertDoesNotThrow(() -> {
            Map<String, String> teacherParams = Map.of(
                    "name", "Super Admin",
                    "userName", "adminUser",
                    "mail", "asd@mail.com",
                    "passwd", "adminPass");

            userFactory.createUser("teacher", teacherParams);
        });
    }

    @Test
    void shouldNotCreateTeacher() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> teacherParams = Map.of(
                    "name", "Super Admin",
                    "userName", "adminUser");

            userFactory.createUser("Teacher", teacherParams);
        });
    }

    @Test
    void shouldNotCreateUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            Map<String, String> studentParams = Map.of(
                    "name", "Super Admin",
                    "userName", "adminUser");

            userFactory.createUser("Student", studentParams);
        });
    }
}
