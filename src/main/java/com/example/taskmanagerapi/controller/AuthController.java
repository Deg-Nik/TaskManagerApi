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

/**
 * @author : Nikolai Degtiarev
 * created : 17.03.26
 *
 *
 **/
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);

        user.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
    //  userDetails.getUsername()
        User user = userService.findByUsername(userDetails.getUsername());
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
