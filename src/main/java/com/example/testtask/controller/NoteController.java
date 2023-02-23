package com.example.testtask.controller;

import com.example.testtask.dto.NewNoteDto;
import com.example.testtask.dto.NoteDto;
import com.example.testtask.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public NoteDto create(@Valid @RequestBody NewNoteDto noteDto) {
        return noteService.create(noteDto);
    }

    @GetMapping
    public Page<NoteDto> getAll(
            @PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return noteService.getAll(pageable);
    }

    @PostMapping("/{note-id}/likes")
    public void setLike(@PathVariable("note-id") String noteId) {
        noteService.addLike(noteId);
    }

    @DeleteMapping("/{note-id}/likes")
    public void deleteLike(@PathVariable("note-id") String noteId) {
        noteService.deleteLike(noteId);
    }
}
