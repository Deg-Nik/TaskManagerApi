package com.example.taskmanagerapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author : Nikolai Degtiarev
 * created : 15.03.26
 * consultation
 *
 **/
@Data
public class CreateTaskRequest {
    @NotBlank(message = "Название задачи не может быть пустым")
    @Size(min = 2, max = 200, message = "Название задачи до 200")
    private String title;

    @Size(max = 1000, message = "Описание задачи до 1000")
    private String description;
}
