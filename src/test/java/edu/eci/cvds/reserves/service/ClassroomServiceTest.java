package edu.eci.cvds.reserves.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import edu.eci.cvds.reserves.repository.ClassroomRepository;

public class ClassroomServiceTest {

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldCreateClassroom() {
    }

}