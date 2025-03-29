package edu.eci.cvds.reserves.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.model.UserAuth;
import edu.eci.cvds.reserves.repository.UserRepository;

/**
 * Test class for UserAuthService.
 */
class UserAuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAuthService userAuthService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoaduserByUsername() {
        User user = new User("John Doe", "juan.jose-j", "password123", "Admin", "john@example.com");
        when(userRepository.findByUsername("juan.jose-j")).thenReturn(user);
        UserAuth userLogin = userAuthService.loadUserByUsername("juan.jose-j");
        assertNotNull(userLogin);
    }

    @Test
    void shouldnotLoaduserByUsername() {
        when(userRepository.findByUsername("juan.jose-j")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userAuthService.loadUserByUsername("juan.jose-j");
        });
    }

}