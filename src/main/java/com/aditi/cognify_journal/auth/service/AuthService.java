package com.aditi.cognify_journal.auth.service;

import com.aditi.cognify_journal.auth.dto.LoginRequestDto;
import com.aditi.cognify_journal.auth.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}