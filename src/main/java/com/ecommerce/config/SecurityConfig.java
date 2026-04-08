package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.security.JwtAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Stateless JWT auth: CSRF not needed for APIs.
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints that do not require authentication.
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/users/register",
                                "/api/users/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Public read-only catalog access.
                        .requestMatchers(HttpMethod.GET,
                                "/api/products/**",
                                "/api/categories/**").permitAll()
                        // Catalog writes are admin-only.
                        .requestMatchers(HttpMethod.POST,
                                "/api/products/**",
                                "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/products/**",
                                "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/products/**",
                                "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );

        http
                // Ensure Spring Security does not create HTTP sessions for JWT auth.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Validate JWT before the username/password auth filter runs.
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt is a safe default for password hashing.
        return new BCryptPasswordEncoder();
    }

}
