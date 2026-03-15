package com.example.taskmanagerapi.repository;


import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.enums.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("TaskRepository Tests")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Сохранение задачи")
    void saveTask() {
        // 1. Создаём пользователя
        User user = new User();
        user.setUsername("nikolai");
        user.setPassword("pass");
        user = userRepository.save(user);

        // 2. Создаём задачу
        Task task = new Task();
        task.setTitle("A");
        task.setDescription("B");
        task.setStatus(TaskStatus.TODO);
        task.setOwner(user); // ← обязательно!

        // 3. Сохраняем
        taskRepository.save(task);

        assertThat(task.getId()).isNotNull();
    }

    @Test
    @DisplayName("Поиск по статусу")
    void findByStatus() {
        // 1. Создаём пользователя
        User user = new User();
        user.setUsername("nikolai");
        user.setPassword("pass");
        user = userRepository.save(user);

        // 2. Создаём задачи с владельцем
        Task t1 = new Task();
        t1.setTitle("A");
        t1.setDescription("B");
        t1.setStatus(TaskStatus.TODO);
        t1.setOwner(user);

        Task t2 = new Task();
        t2.setTitle("C");
        t2.setDescription("D");
        t2.setStatus(TaskStatus.DONE);
        t2.setOwner(user);

        taskRepository.save(t1);
        taskRepository.save(t2);

        // 3. Проверяем
        List<Task> result = taskRepository.findByStatus(TaskStatus.TODO);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("A");
    }
}

