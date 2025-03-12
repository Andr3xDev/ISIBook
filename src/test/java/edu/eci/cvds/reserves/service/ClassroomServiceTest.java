package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
    Classroom classroom2;
    List<Classroom> classrooms;

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private ClassroomService classroomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        classroom = new Classroom();
        classroom2 = new Classroom();
        classrooms = new ArrayList<>();
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

    @Test
    void shouldNotGetClassroomById() {
        when(classroomRepository.findById("invalid-id")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            classroomService.getClassroomById("invalid-id");
        });

        assertEquals("Classroom not found", exception.getMessage());
    }

    @Test
    void shouldGetAllClassroom() {
        classrooms.add(classroom);
        classrooms.add(classroom2);
        when(classroomRepository.findAll()).thenReturn(classrooms);

        List<Classroom> allClassrooms = classroomService.getAllClassroom();
        assertNotNull(allClassrooms);
        assertEquals(2, allClassrooms.size());
        verify(classroomRepository, times(1)).findAll();
    }

    @Test
    void shouldGetAllClassroomByBuild() {
        classroom.setBuild("A");
        classroom2.setBuild("A");
        classrooms.add(classroom);
        classrooms.add(classroom2);
        when(classroomRepository.findByBuild("A")).thenReturn(classrooms);

        List<Classroom> allClassrooms = classroomService.getAllClassroomByBuild("A");
        assertNotNull(allClassrooms);
        assertEquals(2, allClassrooms.size());
        verify(classroomRepository, times(1)).findByBuild("A");
    }

}