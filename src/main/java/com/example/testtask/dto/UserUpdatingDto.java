package com.example.testtask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdatingDto {
    @NotNull
    private String username;
}
