package com.example.testtask.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String username;

    private String password;
}
