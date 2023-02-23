package com.example.testtask.controller;

import com.example.testtask.dto.LoginDto;
import com.example.testtask.dto.RegisterDto;
import com.example.testtask.dto.TokenDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
