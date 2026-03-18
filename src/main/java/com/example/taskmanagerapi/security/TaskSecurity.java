package com.example.taskmanagerapi.security;

import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Компонент для проверки прав доступа к задачам
 */
@Component("taskSecurity")
@RequiredArgsConstructor
public class TaskSecurity {
    private final TaskService taskService;

    /**
     * Проверка: является ли пользователь владельцем задачи
     * @param taskId
     * @param authentication
     * @return
     */
    public boolean isOwner(Long taskId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Task task = taskService.findById(taskId);
        String username = authentication.getName();

        return task.getOwner().getUsername().equals(username);
    }
}
