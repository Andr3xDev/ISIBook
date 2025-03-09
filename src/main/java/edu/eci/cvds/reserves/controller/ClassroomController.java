package edu.eci.cvds.reserves.controller;

import org.springframework.beans.factory.annotation.Autowired;
import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.service.ClassroomService;

@RestController
@RequestMapping("/api/users")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public User createUser(@RequestBody Classroom classroom) {
        return classroomService.createClassroom(classroom);
    }
}