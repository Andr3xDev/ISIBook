package edu.eci.cvds.reserves.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

/**
 * Security configuration class for the application.
 * This class configures Spring Security to manage authentication and
 * authorization.
 * It includes beans for authentication management, password encoding, and
 * security filter chains.
 * 
 * Annotations:
 * - @Configuration: Marks this class as a configuration class for Spring.
 * - @EnableWebSecurity: Enables Spring Security's web security support.
 * - @EnableMethodSecurity: Enables method-level security annotations such
 * as @Secured and @RolesAllowed.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    /**
     * AuthenticationManager: Provides the authentication manager for the
     * application, configured using the AuthenticationConfiguration.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Defines a BCryptPasswordEncoder bean for encoding passwords securely.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain to handle HTTP request
     * authorization, login, and logout functionality.
     * 
     * Security Configuration:
     * - Permits access to "/auth/login" and "/auth/logout" endpoints without
     * authentication.
     * - Requires authentication for all other requests.
     * - Configures form-based login with custom parameters for username and
     * password, and a default success URL.
     * - Configures logout functionality with a custom logout URL and a redirect to
     * the login page upon successful logout.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/auth/logout").permitAll()
                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/auth/user", true))
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login"))
                .build();
    }

}