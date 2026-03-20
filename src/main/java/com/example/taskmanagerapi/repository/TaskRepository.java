package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.entity.Task;
import com.example.taskmanagerapi.entity.User;
import com.example.taskmanagerapi.enums.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Найти все задачи конкретного пользователя
    List<Task> findByOwner(User owner);     // select * from tasks where owner_id = owner.id
    // Найти все задачи пользователя по username
    List<Task> findByOwnerUsername(String username);
    List<Task> findByOwnerAndPriority(User owner, TaskPriority priority);
    List<Task> findByPriority(TaskPriority priority);
}


// http://localhost:8080/api/tasks      // GET, POST
// http://localhost:8080/api/users/1    // GET