package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.ChangePasswordRequest;
import com.example.taskmanagerapi.dto.request.RegisterRequest;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Регистрация нового пользователя
     */
    @Transactional
    public User register(RegisterRequest request) {
        // Проверка на уникальность username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким username уже существует");
        }

        // Создаем пользователя
        User user = new User();
        user.setUsername(request.getUsername());

        // ВАЖНО! Хэшируем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // По умолчанию: роль - USER, и активный аккаунт
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        return userRepository.save(user);

    }

    public void changePassword(User user, ChangePasswordRequest request) {

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Старый пароль указан неверно");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
