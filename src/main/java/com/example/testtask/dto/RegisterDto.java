package com.example.testtask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDto {
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
