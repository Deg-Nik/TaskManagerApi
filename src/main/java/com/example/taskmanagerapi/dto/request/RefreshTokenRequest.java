package com.example.taskmanagerapi.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class RefreshTokenRequest {

    @NotBlank(message = "Refresh token обязателен")
    private String refreshToken;
}
