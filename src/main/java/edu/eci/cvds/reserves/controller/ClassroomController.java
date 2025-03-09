package edu.eci.cvds.reserves.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}