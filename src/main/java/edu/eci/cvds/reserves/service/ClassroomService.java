package edu.eci.cvds.reserves.service;

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
}