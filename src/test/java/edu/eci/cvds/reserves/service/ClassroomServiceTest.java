package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.repository.ClassroomRepository;

class ClassroomServiceTest {

    Classroom classroom;

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private ClassroomService classroomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        classroom = new Classroom();
    }

    @Test
    void shouldCreateClassroom() {
        classroom.setId("1");

        when(classroomRepository.save(classroom)).thenReturn(classroom);

        Classroom newClassroom = classroomService.createClassroom(classroom);

        assertNotNull(newClassroom);
        assertEquals("1", newClassroom.getId());
        verify(classroomRepository, times(1)).save(classroom);
    }

    @Test
    void shouldGetClassroomById() {
        classroom.setId("1");
        when(classroomRepository.findById("1")).thenReturn(Optional.of(classroom));
        Classroom newClassroom = classroomService.getClassroomById("1");

        assertNotNull(newClassroom);
        assertEquals("1", newClassroom.getId());
        verify(classroomRepository, times(1)).findById("1");
    }

}