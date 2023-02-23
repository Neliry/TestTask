package com.example.testtask.service;


import com.example.testtask.dto.LoginDto;
import com.example.testtask.dto.RegisterDto;
import com.example.testtask.dto.TokenDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.entity.UserEntity;
import com.example.testtask.exeption.ConflictException;
import com.example.testtask.meta.ErrorMessages;
import com.example.testtask.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public UserDto register(@RequestBody RegisterDto registerDto) {
        if (userService.isExist(registerDto.getEmail())) {
            throw new ConflictException(ErrorMessages.USER_ALREADY_EXISTS);
        }
        return userService.create(registerDto);
    }

    public TokenDto login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        UserEntity user = userService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return new TokenDto(tokenProvider.createToken(email));
        } else {
            throw new BadCredentialsException(ErrorMessages.BED_CREDENTIALS);
        }
    }
}
