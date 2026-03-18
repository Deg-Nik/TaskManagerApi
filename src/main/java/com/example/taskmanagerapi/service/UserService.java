package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.RegisterRequest;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

//    public User register(RegisterRequest request) {
//
//        if (userRepository.existsByUsername(request.getUsername())) {
//            throw new RuntimeException("Пользователь с таким username уже существует");
//        }
//
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        return userRepository.save(user);
//    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + username));
    }

//    public User updateStatus(String username, String role) {
//        User currentUser = findByUsername(username);
//
//        return userRepository.setRole(role)
//                .orElseThrow(() -> new RuntimeException("username not update"));
//    }
}
