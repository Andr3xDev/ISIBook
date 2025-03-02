package edu.eci.cvds.reserves.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.reserves.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String userName);

    List<User> findAllByType(String type);

    @Query("{ 'userName': ?0 }")
    @Update("{ '$set': { 'status': ?1 } }")
    User updateStatusByUserName(String userName, String status);

}