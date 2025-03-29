package edu.eci.cvds.reserves.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.service.ClassroomService;

/**
 * REST controller for managing classrooms.
 * Provides endpoints for creating, retrieving, and searching classrooms.
 */
@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    /**
     * Creates a new classroom.
     *
     * @param classroom the classroom to create
     * @return a ResponseEntity containing the created classroom or an error status
     */
    @PostMapping("/create")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom createdClassroom = classroomService.createClassroom(classroom);
        if (createdClassroom == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdClassroom);
    }

    /**
     * Retrieves a classroom by its ID.
     *
     * @param id the ID of the classroom
     * @return a ResponseEntity containing the classroom or a not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Classroom> findClassroomById(@PathVariable String id) {
        Classroom classroom = classroomService.getClassroomById(id);
        if (classroom == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classroom);
    }

    /**
     * Retrieves all classrooms.
     *
     * @return a ResponseEntity containing the list of all classrooms or an error
     *         message
     */
    @GetMapping
    public ResponseEntity<List<Classroom>> findAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassroom();
        if (classrooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(classrooms);
    }

    /**
     * Retrieves classrooms by their building.
     *
     * @param build the building name
     * @return a ResponseEntity containing the list of classrooms in the specified
     *         building or a not found status
     */
    @GetMapping("/building")
    public ResponseEntity<List<Classroom>> findAllClassroomsByBuilding(@RequestParam String build) {
        List<Classroom> classrooms = classroomService.getAllClassroomByBuild(build);
        if (classrooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classrooms);
    }

}