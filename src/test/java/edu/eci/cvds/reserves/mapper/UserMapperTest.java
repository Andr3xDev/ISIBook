package edu.eci.cvds.reserves.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.reserves.dto.UserCreateDto;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.model.User;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void shouldMapUserToDto() {
        User admin = new User("John Doe", "johndoe", "password123", "Admin", "john@example.com");
        UserDto userDto = userMapper.toDto(admin);

        assertNotNull(userDto);
        assertEquals(admin.getName(), userDto.getName());
        assertEquals(admin.getRegister(), userDto.getRegister());
        assertEquals(admin.getId(), userDto.getId());
    }

    @Test
    void shouldNotMapUserToDto() {
        User admin = null;
        UserDto userDto = userMapper.toDto(admin);
        assertNull(userDto);
    }

    @Test
    void shouldMapDtoToUser() {
        User admin = new User("John Doe", "johndoe", "password123", "Admin", "john@example.com");
        UserDto userDto = userMapper.toDto(admin);
        User userFromDto = userMapper.toEntity(userDto);

        assertNotNull(userFromDto);
        assertEquals(userFromDto.getName(), admin.getName());
        assertEquals(userFromDto.getRegister(), admin.getRegister());
        assertEquals(userFromDto.getId(), admin.getId());
    }

    @Test
    void shouldNotMapDtoToUser() {
        User admin = null;
        UserDto userDto = userMapper.toDto(admin);
        User userFromDto = userMapper.toEntity(userDto);

        assertNull(userFromDto);
    }

    @Test
    void shouldMapEntityCreation() {
        UserCreateDto admin = new UserCreateDto("John Doe", "johndoe", "password123", "Admin", "john@example.com");
        User userDto = userMapper.toEntity(admin);

        assertNotNull(userDto);
        assertEquals(userDto.getName(), admin.getName());
        assertEquals(userDto.getMail(), admin.getMail());
        assertEquals(userDto.getType(), admin.getType());
    }

    @Test
    void shouldNotMapEntityCreation() {
        UserCreateDto admin = null;
        User userDto = userMapper.toEntity(admin);
        assertNull(userDto);
    }
}