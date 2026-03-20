package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.request.CreateTaskRequest;
import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.service.TaskService;
import com.example.taskmanagerapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final UserService userService;
    private final TaskService taskService;

    /**
     * Получить МОИ задачи
     * Доступно всем авторизованным пользователям
     */
    @GetMapping
    public ResponseEntity<List<Task>> getMyTasks(
            @AuthenticationPrincipal UserDetails userDetails) {

        User currentUser = userService.findByUsername(userDetails.getUsername());
        List<Task> tasks = taskService.findByOwner(currentUser);

        return ResponseEntity.ok(tasks);
    }

    /**
     * Получить ВСЕ задачи (Доступно ТОЛЬКО для ADMIN)
     *
     * @PreAuthorize проверяет роль ПЕРЕД выполнением метода
     * Если роли нет -> 403 Forbidden
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Создать новую задачу
     * Владельцем становится текущий пользователь
     */
    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody CreateTaskRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User currentUser = userService.findByUsername(userDetails.getUsername());
        Task task = taskService.create(request, currentUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}
