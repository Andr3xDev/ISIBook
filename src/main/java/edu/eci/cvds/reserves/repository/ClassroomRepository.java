package edu.eci.cvds.reserves.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.reserves.model.Classroom;

/**
 * Repository interface for Classroom entity.
 * Provides methods to perform CRUD operations on Classroom collection.
 */
@Repository
public interface ClassroomRepository extends MongoRepository<Classroom, String> {

    List<Classroom> findByBuild(String build);

}