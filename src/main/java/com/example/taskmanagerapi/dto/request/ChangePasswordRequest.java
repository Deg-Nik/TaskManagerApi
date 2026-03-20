package com.example.taskmanagerapi.dto.request;

import lombok.Data;

/**
 * @author : Nikolai Degtiarev
 * created : 20.03.26
 *
 *
 **/
@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
