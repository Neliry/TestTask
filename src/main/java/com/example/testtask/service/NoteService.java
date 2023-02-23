package com.example.testtask.service;

import com.example.testtask.dto.NewNoteDto;
import com.example.testtask.dto.NoteDto;
import com.example.testtask.entity.NoteEntity;
import com.example.testtask.exeption.NotFoundException;
import com.example.testtask.mapper.NoteMapper;
import com.example.testtask.meta.ErrorMessages;
import com.example.testtask.repository.NoteRepository;
import com.example.testtask.utils.DateUtils;
import com.example.testtask.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {
    private final NoteMapper mapper;
    private final NoteRepository noteRepository;

    public NoteDto create(NewNoteDto noteDto) {
        NoteEntity note = mapper.toEntity(noteDto);
        note.setCreationDate(DateUtils.getCurrentUtc());
        return mapper.toDto(noteRepository.save(note));
    }

    public Page<NoteDto> getAll(Pageable pageable) {
        return noteRepository.findAll(pageable).map(mapper::toDto);
    }

    public void addLike(String noteId) {
        String userId = SecurityContextUtils.getPrincipal().getId();
        NoteEntity note = getById(noteId);
        note.getLikedUsersIds().add(userId);
        noteRepository.save(note);
    }

    public void deleteLike(String noteId) {
        String userId = SecurityContextUtils.getPrincipal().getId();
        NoteEntity note = getById(noteId);
        note.getLikedUsersIds().remove(userId);
        noteRepository.save(note);
    }

    public void deleteLike(String noteId, String userId) {
        NoteEntity note = getById(noteId);
        note.getLikedUsersIds().remove(userId);
        noteRepository.save(note);
    }

    private NoteEntity getById(String noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new NotFoundException(ErrorMessages.NOTE_NOT_FOUND));
    }

    public void deleteLikeByUserId(String userId) {
        List<NoteEntity> notes = noteRepository.findAllByLikedUsersIds(userId);
        notes.forEach(note -> deleteLike(note.getId(), userId));
    }
}
