package com.example.testtask.repository;

import com.example.testtask.entity.NoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {
    List<NoteEntity> findAllByLikedUsersIds(String userId);
}
