package edu.eci.cvds.reserves.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.model.UserAuth;
import edu.eci.cvds.reserves.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAuth loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new UserAuth(user);
    }

}