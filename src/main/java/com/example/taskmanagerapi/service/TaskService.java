package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.CreateTaskRequest;
import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.enums.TaskStatus;
import com.example.taskmanagerapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Получить все задачи (для ADMIN)
     */
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    /**
     * Получить все задачи текущего пользователя
     */
    public List<Task> findByOwner(User owner) {
        return taskRepository.findByOwner(owner);
    }

    /**
     * Найти задачу по ID
     */
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача с ID: " + id + " не найдена"));
    }

    /**
     * Создать новую задачу
     */
    @Transactional
    public Task create(CreateTaskRequest request, User owner) {
        Task newTask = new Task();
        newTask.setTitle(request.getTitle());
        newTask.setDescription(request.getDescription());
        newTask.setStatus(TaskStatus.TODO);
        newTask.setOwner(owner);

        return taskRepository.save(newTask);
    }

    /**
     * Обновить задачу
     */
    @Transactional
    public Task update(Long id, CreateTaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        return taskRepository.save(task);
    }

    /**
     * Обновить статус задачи
     */
    @Transactional
    public Task updateStatus(Long id, TaskStatus status) {
        Task task = findById(id);
        task.setStatus(status);

        return taskRepository.save(task);
    }

    /**
     * Удалить задачу
     */
    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }

}
