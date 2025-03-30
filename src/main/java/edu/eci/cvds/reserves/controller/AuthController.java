package edu.eci.cvds.reserves.controller;

import edu.eci.cvds.reserves.dto.AuthRequest;
import edu.eci.cvds.reserves.dto.AuthResponse;
import edu.eci.cvds.reserves.dto.UserDto;
import edu.eci.cvds.reserves.config.JwtService;
import edu.eci.cvds.reserves.service.UserAuthService;
import edu.eci.cvds.reserves.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAuthService userAuthService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
            UserAuthService userAuthService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userAuthService = userAuthService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDto userFind = userService.findUserByUsername(authRequest.getUsername());
        UserDetails user = userAuthService.loadUserByUsername(authRequest.getUsername());
        String token = jwtService.generateToken(user.getUsername(), userFind.getType());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
