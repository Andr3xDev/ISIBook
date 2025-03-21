package edu.eci.cvds.reserves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw new RuntimeException("TODO");
        }
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> findAllUserTeacher() {
        return userRepository.findAllByType("Teacher");
    }

    public List<User> findAllUserAdmin() {
        return userRepository.findAllByType("Admin");
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}