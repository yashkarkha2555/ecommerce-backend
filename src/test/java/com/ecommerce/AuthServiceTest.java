package com.ecommerce;

import com.ecommerce.dto.AuthRequest;
import com.ecommerce.dto.AuthResponse;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.EmailAlreadyRegisteredException;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registerAndLoginReturnsToken() {
        RegisterRequest register = new RegisterRequest();
        register.setName("Test User");
        register.setEmail("test@example.com");
        register.setPassword("password123");

        UserDTO user = authService.register(register);
        assertNotNull(user.getId());
        assertTrue(userRepository.existsByEmail("test@example.com"));

        AuthRequest login = new AuthRequest();
        login.setEmail("test@example.com");
        login.setPassword("password123");

        AuthResponse response = authService.login(login);
        assertNotNull(response.getToken());
    }

    @Test
    void registerDuplicateEmailThrows() {
        RegisterRequest register = new RegisterRequest();
        register.setName("Test User");
        register.setEmail("dup@example.com");
        register.setPassword("password123");

        authService.register(register);

        assertThrows(EmailAlreadyRegisteredException.class,
                () -> authService.register(register));
    }
}
