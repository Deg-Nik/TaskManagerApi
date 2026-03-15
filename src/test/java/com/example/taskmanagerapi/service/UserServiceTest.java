package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("UserService Tests")
class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

        userService = new UserService(userRepository, passwordEncoder);
    }


    @Test
    @DisplayName("findByUsername — найден")
    void findByUsername_found() {
        User user = new User(1L, "nikolai", "pass");
        when(userRepository.findByUsername("nikolai")).thenReturn(Optional.of(user));

        User result = userService.findByUsername("nikolai");

        assertThat(result.getId()).isEqualTo(1L);
    }
}

