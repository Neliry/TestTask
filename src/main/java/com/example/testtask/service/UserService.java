package com.example.testtask.service;

import com.example.testtask.dto.ChangePasswordDto;
import com.example.testtask.dto.RegisterDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.dto.UserUpdatingDto;
import com.example.testtask.entity.UserEntity;
import com.example.testtask.exeption.NotFoundException;
import com.example.testtask.mapper.UserMapper;
import com.example.testtask.meta.ErrorMessages;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final NoteService noteService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(RegisterDto registerDto) {
        UserEntity user = mapper.toEntity(registerDto);
        String password = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(password);
        return mapper.toDto(userRepository.save(user));
    }

    public boolean isExist(String email) {
        return getByEmail(email).isPresent();
    }

    public UserDto getCurrent() {
        String id = SecurityContextUtils.getPrincipal().getId();
        UserEntity user = getById(id);
        return mapper.toDto(user);
    }

    private UserEntity getById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));
    }

    public Page<UserDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(mapper::toDto);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public void update(UserUpdatingDto userDto) {
        String userId = SecurityContextUtils.getPrincipal().getId();
        UserEntity user = getById(userId);
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        String email = SecurityContextUtils.getPrincipal().getUsername();
        UserEntity user = getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if (passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException(ErrorMessages.BED_CREDENTIALS);
        }
    }

    public void deleteCurrent() {
        String id = SecurityContextUtils.getPrincipal().getId();
        noteService.deleteLikeByUserId(id);
        userRepository.deleteById(id);
    }
}
