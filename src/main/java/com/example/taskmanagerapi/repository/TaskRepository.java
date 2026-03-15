package com.example.taskmanagerapi.repository;


import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwnerId(Long ownerId);
    List<Task> findByStatus(TaskStatus status);

    // Найти задачи пользователя
    List<Task> findByOwner(User owner);
    // Найти задачи пользователя по username
    List<Task> findByOwnerUsername(String username);

}

