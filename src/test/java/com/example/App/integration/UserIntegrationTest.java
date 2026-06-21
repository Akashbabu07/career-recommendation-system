package com.example.App.integration;

import com.example.App.dto.response.UserResponse;
import com.example.App.entity.User;
import com.example.App.repository.UserRepository;
import com.example.App.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanDb() {
        userRepository.deleteAll();
    }

    @Test
    void testUserFlow_registerAndFetch() {

        User user = new User();
        user.setName("Akash");
        user.setEmail("akash@test.com");
        user.setPassword("123456");

        userRepository.save(user);

        UserResponse response =
                userService.getProfile("akash@test.com");

        assertNotNull(response);
        assertEquals("Akash", response.getName());
        assertEquals("akash@test.com", response.getEmail());
    }
}
