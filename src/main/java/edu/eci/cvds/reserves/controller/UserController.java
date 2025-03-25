package edu.eci.cvds.reserves.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.reserves.dto.UserCreateDto;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.service.UserService;

import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users.
     *
     * @return a ResponseEntity containing the list of all users or an error message
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUser();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    /**
     * Get a user by ID.
     *
     * @param id the ID of the user
     * @return a ResponseEntity containing the user or a not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get a user by ID.
     *
     * @param id the ID of the user
     * @return a ResponseEntity containing the user or a not found status
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = userService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Create a new user.
     *
     * @param user the user to create
     * @return a ResponseEntity containing the created user
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) {
        User createdUser = userService.createUser(userCreateDto);

        return ResponseEntity.ok(createdUser);
    }

    /**
     * Update a user by ID.
     *
     * @param id   the ID of the user to update
     * @param user the updated user data
     * @return a ResponseEntity containing the updated user or a not found status
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return null;
    }

    /**
     * Delete a user by username.
     *
     * @param username the username of the user to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        if (userService.findUserByUsername(username) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }
}