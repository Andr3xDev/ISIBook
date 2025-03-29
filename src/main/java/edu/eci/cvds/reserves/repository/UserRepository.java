package edu.eci.cvds.reserves.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.reserves.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    List<User> findAllByType(String type);

}