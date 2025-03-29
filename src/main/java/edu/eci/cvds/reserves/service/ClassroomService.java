package edu.eci.cvds.reserves.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.repository.ClassroomRepository;

/**
 * Service class for managing classrooms.
 * Provides methods to create, retrieve, and filter classrooms.
 */
@Service
public class ClassroomService {

    ClassroomRepository classroomRepository;

    /**
     * Constructor for ClassroomService.
     *
     * @param classroomRepository the repository to be used for classroom operations
     */
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    /**
     * Creates a new classroom.
     *
     * @param classroom the classroom to be created
     * @return the created classroom
     */
    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    /**
     * Retrieves a classroom by its ID.
     *
     * @param id the ID of the classroom to be retrieved
     * @return the classroom with the specified ID
     */
    public Classroom getClassroomById(String id) {
        return classroomRepository.findById(id).orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

    /**
     * Retrieves all classrooms.
     *
     * @return a list of all classrooms
     */
    public List<Classroom> getAllClassroom() {
        return classroomRepository.findAll();
    }

    /**
     * Retrieves all classrooms in a specific building.
     *
     * @param build the building to filter classrooms by
     * @return a list of classrooms in the specified building
     */
    public List<Classroom> getAllClassroomByBuild(String build) {
        return classroomRepository.findByBuild(build);
    }

}