package edu.eci.cvds.reserves.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.mapper.UserMapper;
import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    /**
     * Constructor for UserService, it injects all dependencies.
     *
     * @param userRepository The repository for user data access.
     * @param userMapper     The mapper for converting user entities.
     */
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     * @return The created user.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their username.
     *
     * @param username The username of the user to be deleted.
     * @throws RuntimeException If the user does not exist.
     */
    public void deleteUserByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw new RuntimeException("TODO");
        }
    }

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return An Optional containing the user if found, or empty if not found.
     */
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The user if found, or null if not found.
     */
    public UserDto findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto)
                .orElse(null);
    }

    /**
     * Retrieves all users with the type "Teacher".
     *
     * @return A list of users with the type "Teacher".
     */
    public List<User> findAllUserTeacher() {
        return userRepository.findAllByType("Teacher");
    }

    /**
     * Retrieves all users with the type "Admin".
     *
     * @return A list of users with the type "Admin".
     */
    public List<User> findAllUserAdmin() {
        return userRepository.findAllByType("Admin");
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<UserDto> findAllUser() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto)
                .toList();
    }
}