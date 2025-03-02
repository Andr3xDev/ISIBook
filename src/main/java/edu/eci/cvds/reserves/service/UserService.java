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

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
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

    public void updateStatusByUserName(String userName, String status) {
        userRepository.updateStatusByUserName(userName, status);
    }

}
