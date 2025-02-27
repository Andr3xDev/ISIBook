package edu.eci.cvds.reserves.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.eci.cvds.reserves.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    default User createUser(User user) {
        Optional<User> findUserByName = findByName(user.getName());

        if (findUserByName.isPresent()) {
            // TODO: Do better exceptions
            throw new RuntimeException("Exit");
        }
        return save(save(user));
    }

    /**
     * Find users by ID
     * 
     * @param name
     * @return id
     */
    Optional<User> findByName(String id);
}
