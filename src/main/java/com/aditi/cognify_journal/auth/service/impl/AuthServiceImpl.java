package com.aditi.cognify_journal.auth.service.impl;

import com.aditi.cognify_journal.auth.dto.LoginRequestDto;
import com.aditi.cognify_journal.auth.dto.LoginResponseDto;
import com.aditi.cognify_journal.auth.jwt.JwtService;
import com.aditi.cognify_journal.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        String token = jwtService.generateToken(loginRequestDto.getEmail());

        return LoginResponseDto.builder()
                .token(token)
                .build();
    }
}
