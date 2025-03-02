package edu.eci.cvds.reserves.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.eci.cvds.reserves.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String userName);

    List<User> findAllByType(String type);

}