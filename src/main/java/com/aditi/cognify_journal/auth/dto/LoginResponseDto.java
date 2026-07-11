package com.aditi.cognify_journal.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
}
