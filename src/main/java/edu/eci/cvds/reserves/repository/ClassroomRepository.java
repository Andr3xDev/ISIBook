package edu.eci.cvds.reserves.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.reserves.model.Classroom;

@Repository
public interface ClassroomRepository extends MongoRepository<Classroom, String> {

    List<Classroom> findByBuild(String build);
}