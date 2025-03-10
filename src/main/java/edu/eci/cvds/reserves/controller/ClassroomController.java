package edu.eci.cvds.reserves.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.service.ClassroomService;

@RestController
@RequestMapping("/api/users")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public Classroom createUser(@RequestBody Classroom classroom) {
        return classroomService.createClassroom(classroom);
    }

    @GetMapping
    public ResponseEntity<?> findClassroomById(String id) {
        Classroom classroom = classroomService.getClassroomById(id);
        if (classroom == null || classroom.getId().isEmpty()) {
            return ResponseEntity.badRequest().body("Classroom not found");
        }
        return ResponseEntity.ok(classroom);
    }

    @GetMapping
    public ResponseEntity<?> findAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassroom();
        if (classrooms == null) {
            return ResponseEntity.badRequest().body("There are not classrooms");
        }
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping
    public ResponseEntity<?> findAllClassroomsById(String build) {
        List<Classroom> classrooms = classroomService.getAllClassroomByBuild(build);
        if (classrooms == null) {
            return ResponseEntity.badRequest().body("The build does not exist");
        }
        return ResponseEntity.ok(classrooms);
    }

}