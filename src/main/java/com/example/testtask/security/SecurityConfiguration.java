package com.example.testtask.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtConfigurer jwtConfigurer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/notes").permitAll()
                        .requestMatchers("/notes").permitAll()
                        .anyRequest().authenticated()
                ).apply(jwtConfigurer);
        return http.build();
    }
}

