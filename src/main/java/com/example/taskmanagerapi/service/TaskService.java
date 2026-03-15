package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.enums.TaskStatus;
import com.example.taskmanagerapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена: " + id));
    }

    public Task create(Task task) {
        task.setStatus(TaskStatus.TODO); // дефолтный статус
        return taskRepository.save(task);
    }

    public Task update(Long id, Task updated) {
        Task existing = findById(id);

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());

        return taskRepository.save(existing);
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Задача не найдена: " + id);
        }
        taskRepository.deleteById(id);
    }

    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}

