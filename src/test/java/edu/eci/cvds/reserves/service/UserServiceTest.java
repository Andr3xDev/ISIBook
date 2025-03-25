package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.repository.UserRepository;

class UserServiceTest {

    private User teacher;
    private User admin;
    private ArrayList<User> users;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new User("John Doe", "johndoe", "password123", "Admin", "john@example.com");
        teacher = new User("Jane Smith", "janesmith", "securepass", "Teacher", "jane@example.com");

        users = new ArrayList<User>();
        users.add(admin);
        users.add(teacher);
    }

    @Test
    void shouldCreateUserAdmin() {
        when(userRepository.save(admin)).thenReturn(admin);
        when(userRepository.findByUsername(admin.getUsername())).thenReturn(Optional.of(admin));
        User createdUser = userService.createUser(admin);

        assertNotNull(createdUser);
        assertEquals(admin.getUsername(), createdUser.getUsername());
    }

    @Test
    void shouldDeleteUserByUsername() {
        when(userRepository.existsByUsername("juan.jose-j")).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteUserByUsername("juan.jose-j"));
        verify(userRepository, times(1)).deleteByUsername("juan.jose-j");
    }

    @Test
    void shouldNotDeleteUserByUsername() {
        when(userRepository.existsByUsername("juan.jose-j")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.deleteUserByUsername("juan.jose-j"));
        verify(userRepository, never()).deleteByUsername("juan.jose-j");
    }

    @Test
    void shouldFindAllUserAdmin() {
        List<User> admins = new ArrayList<>();
        admins.add(admin);

        when(userRepository.findAllByType("Admin")).thenReturn(admins);

        List<User> usersFind = userService.findAllUserAdmin();

        assertNotNull(usersFind);
        assertEquals(1, usersFind.size());
        assertNotEquals(users.size(), usersFind.size());
    }

    @Test
    void shouldFindAllUserTeacher() {
        List<User> teachers = new ArrayList<>();
        teachers.add(teacher);

        when(userRepository.findAllByType("Teacher")).thenReturn(teachers);

        List<User> usersFind = userService.findAllUserTeacher();

        assertNotNull(usersFind);
        assertEquals(1, usersFind.size());
        assertNotEquals(users.size(), usersFind.size());
    }

    @Test
    void shouldFindAllUser() {
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> usersFind = userService.findAllUser();

        assertNotNull(usersFind);
        assertEquals(2, usersFind.size());
        assertEquals(users.size(), usersFind.size());
    }

    @Test
    void shouldFindUserByusername() {
        when(userRepository.save(teacher)).thenReturn(teacher);
        when(userRepository.findByUsername(teacher.getUsername())).thenReturn(Optional.of(teacher));

        User createdUser = userService.createUser(teacher);
        User targetUser = userService.findUserByUsername(createdUser.getUsername());

        assertNotNull(targetUser);
        assertEquals(teacher.getUsername(), targetUser.getUsername());
    }

}