package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.request.RegisterRequest;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.service.AuthService;
import com.example.taskmanagerapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    /**
     * Регистрация нового пользователя
     * Доступно всем (публичный endpoint)
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);

        // Не возвращаем пароль клиенту!
        user.setPassword(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * Получение информации о текущем пользователе
     * @AuthenticationPrincipal - Spring Security автоматически подставляет
     * информацию о вошедшем пользователе
     */
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        // userDetails.getUsername() - имя вошедшего пользователя
        User user = userService.findByUsername(userDetails.getUsername());
        user.setPassword(null);     // Не возвращаем пароль

        return ResponseEntity.ok(user);
    }
}
