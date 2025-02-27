package edu.eci.cvds.reserves.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import edu.eci.cvds.reserves.model.User;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateAdmin() {
    }

    @Test
    void shouldNotCreateAdmin() {
    }

    @Test
    void shouldCreateUserTeacher() {
    }

    @Test
    void shouldNotCreateTeacher() {
    }
}
