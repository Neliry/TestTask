package com.example.testtask.security;

import com.example.testtask.entity.UserEntity;
import com.example.testtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity user = userService.getByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException(userEmail));
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), true, authorities);
    }
}
