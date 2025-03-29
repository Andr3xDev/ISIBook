package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import edu.eci.cvds.reserves.dto.UserCreateDto;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.mapper.UserMapper;
import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.repository.UserRepository;

class UserServiceTest {

    private User teacher;
    private User admin;
    private UserCreateDto userDto;
    private UserDto userDto2;
    private ArrayList<User> users;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new User("John Doe", "juan.jose-j", "password123", "Admin", "john@example.com");
        teacher = new User("Jane Smith", "janesmith", "securepass", "Teacher", "jane@example.com");

        userDto = new UserCreateDto();
        userDto.setName("John Doe");
        userDto.setUsername("johndoe");
        userDto.setPassword("password123");
        userDto.setType("Admin");
        userDto.setMail("john@example.com");

        userDto2 = new UserDto();
        userDto2.setName("John Doe");
        userDto2.setUsername("johndoe");
        userDto2.setType("Admin");
        userDto2.setMail("john@example.com");
        userDto2.setStatus("Inactive");

        users = new ArrayList<User>();
        users.add(admin);
        users.add(teacher);
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.save(admin)).thenReturn(admin);
        when(userMapper.toEntity(userDto)).thenReturn(admin);

        User createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals(admin.getUsername(), createdUser.getUsername());
    }

    @Test
    void shouldNotCreateUser() {
        when(userRepository.existsByUsername("johndoe")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            userService.createUser(userDto);
        });
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
    void shouldUpdateUserStatus() {
        when(userRepository.findByUsername("juan.jose-j")).thenReturn(admin);
        when(userRepository.save(any(User.class))).thenReturn(admin);
        when(userMapper.toDto(admin)).thenReturn(userDto2);

        UserDto userUpdate = userService.updateUserStatusByUsername("juan.jose-j", "Inactive");

        assertDoesNotThrow(() -> userService.updateUserStatusByUsername("juan.jose-j", "Inactive"));
        assertEquals(admin.getStatus(), userUpdate.getStatus());
    }

    @Test
    void shouldNotUpdateUserStatus() {
        when(userRepository.findByUsername("juan.jose-noExiste")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            userService.updateUserStatusByUsername("juan.jose-noExiste", "Suspended");
        });
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
        when(userRepository.findByUsername(admin.getUsername())).thenReturn(admin);
        when(userMapper.toDto(admin)).thenReturn(userDto2);

        UserDto resultDto = userService.findUserByUsername(admin.getUsername());

        assertNotNull(resultDto);
        assertEquals(userDto2.getUsername(), resultDto.getUsername());
        assertEquals(userDto2.getName(), resultDto.getName());
        verify(userRepository, times(1)).findByUsername(admin.getUsername());
    }

    @Test
    void shouldNotFindUserByusername() {
        when(userRepository.findByUsername("jose jose")).thenReturn(null);

        UserDto resultDto = userService.findUserByUsername("jose jose");

        assertNull(resultDto);
        verify(userRepository, times(1)).findByUsername("jose jose");
    }

    @Test
    void shouldFindUserById() {
        when(userRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        when(userMapper.toDto(admin)).thenReturn(userDto2);

        UserDto resultDto = userService.findUserById(admin.getId());

        assertNotNull(resultDto);
        assertEquals(userDto2.getUsername(), resultDto.getUsername());
        assertEquals(userDto2.getName(), resultDto.getName());
        verify(userRepository, times(1)).findById(admin.getId());
    }

    @Test
    void shouldNotFindUserById() {
        when(userRepository.findById("123")).thenReturn(Optional.empty());

        UserDto resultDto = userService.findUserById("123");

        assertNull(resultDto);
        verify(userRepository, times(1)).findById("123");
    }
}