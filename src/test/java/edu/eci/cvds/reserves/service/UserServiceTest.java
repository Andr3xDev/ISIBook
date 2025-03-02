package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.repository.UserRepository;
import edu.eci.cvds.reserves.utils.factory.UserFactory;

class UserServiceTest {

    private UserFactory userFactory;
    private User teacher;
    private User admin;
    private ArrayList<User> users;

    private Map<String, String> paramsTeacher = Map.of(
            "name", "Juan Jose",
            "userName", "juan.jose-j",
            "mail", "juan.jose-j@escuelaing.edu.co",
            "passwd", "juanito");

    private Map<String, String> paramsAdmin = Map.of(
            "name", "Super Admin",
            "userName", "adminUser",
            "passwd", "adminPass");

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userFactory = new UserFactory();
        admin = userFactory.createUser("Admin", paramsAdmin);
        teacher = userFactory.createUser("Teacher", paramsTeacher);

        users = new ArrayList<User>();
        users.add(admin);
        users.add(teacher);
    }

    @Test
    void shouldCreateUserAdmin() {
        when(userRepository.save(admin)).thenReturn(admin);
        when(userRepository.findByUserName(admin.getUserName())).thenReturn(Optional.of(admin));

        User createdUser = userService.createUser(admin);

        assertNotNull(createdUser);
        assertEquals(admin.getUserName(), createdUser.getUserName());
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

        List<User> usersFind = userService.findAllUser();

        assertNotNull(usersFind);
        assertEquals(2, usersFind.size());
        assertEquals(users.size(), usersFind.size());
    }

    // @Test
    void shouldFindUserByID() {
        // when(userRepository.findById("1").thenReturn(Optional.of(admin));

        Optional<User> targetUser = userRepository.findById("1");

        System.out.println(targetUser);
        assertNotNull(targetUser.get());
    }

    @Test
    void shouldFindUserByUserName() {
        when(userRepository.save(teacher)).thenReturn(teacher);
        when(userRepository.findByUserName(teacher.getUserName())).thenReturn(Optional.of(teacher));

        User createdUser = userService.createUser(teacher);
        User targetUser = userService.findUserByUserName(createdUser.getUserName());

        assertNotNull(targetUser);
        assertEquals(teacher.getUserName(), targetUser.getUserName());
    }

}