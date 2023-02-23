package com.example.testtask.controller;

import com.example.testtask.dto.ChangePasswordDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.dto.UserUpdatingDto;
import com.example.testtask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDto getCurrent() {
        return userService.getCurrent();
    }

    @GetMapping("/all")
    public Page<UserDto> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @PutMapping
    public void update(@Valid @RequestBody UserUpdatingDto user) {
        userService.update(user);
    }

    @PatchMapping("/password")
    public void changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
    }

    @DeleteMapping
    public void delete() {
        userService.deleteCurrent();
    }
}
