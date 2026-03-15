package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.enums.TaskStatus;
import com.example.taskmanagerapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("TaskService Tests")
class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);

        task = new Task(1L, "Test", "Desc", TaskStatus.TODO);
    }

    @Test
    @DisplayName("findById — задача найдена")
    void findById_found() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.findById(1L);

        assertThat(result.getTitle()).isEqualTo("Test");
    }

    @Test
    @DisplayName("findById — задача не найдена")
    void findById_notFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.findById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Задача не найдена");
    }

    @Test
    @DisplayName("create — статус TODO по умолчанию")
    void create_defaultStatus() {
        Task newTask = new Task(null, "New", "Desc", null);

        when(taskRepository.save(any(Task.class))).thenAnswer(inv -> inv.getArgument(0));

        Task saved = taskService.create(newTask);

        assertThat(saved.getStatus()).isEqualTo(TaskStatus.TODO);
    }
}

