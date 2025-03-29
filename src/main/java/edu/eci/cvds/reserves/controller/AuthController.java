package edu.eci.cvds.reserves.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AuthController is a REST controller that handles authentication-related
 * endpoints.
 * It provides functionality for login, retrieving the authenticated user, and
 * logout.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * Handles the login request.
     * This endpoint is managed automatically by Spring Security.
     * It is not necessary to implement any logic here, as Spring Security handles
     * the authentication process.
     * 
     * @return a ResponseEntity indicating the login status
     */
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // El login es manejado automáticamente por Spring Security
        return ResponseEntity.ok("Login exitoso. Sesión iniciada.");
    }

    /**
     * Retrieves the authenticated user.
     * 
     * @return a ResponseEntity containing the authenticated user's details or an
     *         error message
     */
    @GetMapping("/user")
    public ResponseEntity<Object> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(authentication.getPrincipal());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay usuario autenticado");
    }

    /**
     * Handles the logout request.
     * This endpoint is managed automatically by Spring Security.
     * It is not necessary to implement any logic here, as Spring Security handles
     * the logout process.
     * 
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @return a ResponseEntity indicating the logout status
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Logout exitoso. Sesión cerrada.");
    }
}