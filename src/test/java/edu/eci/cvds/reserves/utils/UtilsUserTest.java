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
        Map<String, String> adminParams = Map.of(
                "name", "Super Admin",
                "username", "adminUser",
                "passwd", "adminPass");

        assertDoesNotThrow(() -> {
            userFactory.createUser("Admin", adminParams);
        });
    }

    @Test
    void shouldNotCreateAdmin() {
        Map<String, String> adminParams = Map.of(
                "name", "Super Admin",
                "username", "adminUser");

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Admin", adminParams);
        });
    }

    @Test
    void shouldCreateTeacher() {
        Map<String, String> teacherParams = Map.of(
                "name", "Super Admin",
                "username", "adminUser",
                "mail", "asd@mail.com",
                "passwd", "adminPass");

        assertDoesNotThrow(() -> {
            userFactory.createUser("teacher", teacherParams);
        });
    }

    @Test
    void shouldNotCreateTeacher() {
        Map<String, String> teacherParams = Map.of(
                "name", "",
                "username", "adminUser");

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Teacher", teacherParams);
        });
    }

    @Test
    void shouldNotCreateUser() {
        Map<String, String> studentParams = Map.of(
                "name", "Super Admin",
                "username", "adminUser");

        assertThrows(IllegalArgumentException.class, () -> {
            userFactory.createUser("Student", studentParams);
        });
    }
}
