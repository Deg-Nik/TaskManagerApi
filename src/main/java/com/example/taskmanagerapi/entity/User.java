package com.example.taskmanagerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальный логин пользователя
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Хэшированный пароль (BCrypt)
     * ⚠️ НИКОГДА не храните пароли в открытом виде!
     */
    @Column(nullable = false)
    private String password;

    /**
     * Роль пользователя: USER или ADMIN
     * Префикс ROLE_ обязателен для Spring Security
     */
    @Column(nullable = false)
    private String role = "ROLE_USER";

    /**
     * Активен ли аккаунт
     */
    @Column(nullable = false)
    private Boolean enabled = true;

    /**
     * Задачи пользователя
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
