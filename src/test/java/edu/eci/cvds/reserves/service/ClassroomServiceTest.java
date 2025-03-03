package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.repository.ClassroomRepository;

class ClassroomServiceTest {

    private Classroom classroom1;
    private Classroom classroom2;

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        classroom1 = new Classroom("C302", "Sala de computo", "Computacional");
        classroom2 = new Classroom("H301", "Sala de reuniones", "Reuniones");
    }

    @Test
    void shouldCreateClassroom() {
        when(classroomRepository.save(classroom1)).thenReturn(classroom1);

        Classroom cretedClassroom = classroomRepository.save(classroom1);

        assertNotNull(classroom1);
        assertEquals(classroom1.getId(), cretedClassroom.getId());
    }

}