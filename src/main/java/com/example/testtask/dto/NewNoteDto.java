package com.example.testtask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewNoteDto {
    @NotBlank
    private String content;
}
