package edu.eci.cvds.reserves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.reserves.model.Classroom;
import edu.eci.cvds.reserves.repository.ClassroomRepository;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository classroomRepository;

    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom getClassroomById(String id) {
        return classroomRepository.findById(id).orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

    public List<Classroom> getAllClassroom() {
        return classroomRepository.findAll();
    }

    public List<Classroom> getAllClassroomByBuild(String build) {
        return classroomRepository.findByBuild(build);
    }
}