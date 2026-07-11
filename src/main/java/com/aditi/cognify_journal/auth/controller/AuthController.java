package com.aditi.cognify_journal.auth.controller;

import com.aditi.cognify_journal.auth.dto.LoginRequestDto;
import com.aditi.cognify_journal.auth.dto.LoginResponseDto;
import com.aditi.cognify_journal.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}
