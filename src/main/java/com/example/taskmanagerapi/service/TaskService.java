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

    // Получить все задачи ADMIN
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    //Получить все задачи пользователя
    public List<Task> findByOwner(User owner) {
        return taskRepository.findByOwner(owner);
    }

    //Получить все задачи по ID
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена: " + id));
    }

    //Получить все задачи по Status
    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    // Создать задачу
    @Transactional
    public Task create(CreateTaskRequest request, User owner) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.TODO); // дефолтный статус
        task.setOwner(owner);

        return taskRepository.save(task);
    }

    // Обновить задачу
    @Transactional
    public Task update(Long id, CreateTaskRequest request) {
        Task foundTask = findById(id);
        foundTask.setTitle(request.getTitle());
        foundTask.setDescription(request.getDescription());

        return taskRepository.save(foundTask);
    }

    // Обновить status задач
    @Transactional
    public Task updateStatus(Long id, TaskStatus status) {
        Task foundTask = findById(id);
        foundTask.setStatus(status);
        return taskRepository.save(foundTask);
    }

    // удалить задачу
    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }


}

