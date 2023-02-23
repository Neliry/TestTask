package com.example.testtask.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Document("note")
public class NoteEntity {
    @Id
    private String id;
    private String content;
    private LocalDateTime creationDate;
    private Set<String> likedUsersIds = new HashSet<>();
}
