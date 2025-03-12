package edu.eci.cvds.reserves.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.service.ClassroomService;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping // TODO: add DTO
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom createdClassroom = classroomService.createClassroom(classroom);
        if (createdClassroom == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdClassroom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> findClassroomById(@PathVariable String id) {
        Classroom classroom = classroomService.getClassroomById(id);
        if (classroom == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classroom);
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> findAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassroom();
        if (classrooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/building")
    public ResponseEntity<List<Classroom>> findAllClassroomsByBuilding(@RequestParam String build) {
        List<Classroom> classrooms = classroomService.getAllClassroomByBuild(build);
        if (classrooms.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classrooms);
    }
}