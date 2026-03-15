package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("UserRepository Tests")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Поиск по username")
    void findByUsername() {
        User user = new User(null, "nikolai", "pass");
        userRepository.save(user);

        Optional<User> found = userRepository.findByUsername("nikolai");

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("nikolai");
    }
}

