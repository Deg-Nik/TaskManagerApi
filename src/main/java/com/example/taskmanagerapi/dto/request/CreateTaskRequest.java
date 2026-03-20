package com.example.taskmanagerapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskRequest {    // "A "
    @NotBlank(message = "Название задачи не может быть пустым")
    @Size(min = 2, max = 200, message = "Название задачи должно содержать от 2 до 200 символов")
    private String title;

    @Size(max = 1000, message = "Описание задачи не может содержать более 1000 символов")
    private String description;

}
