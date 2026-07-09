package com.aditi.cognify_journal.user.mapper;

import com.aditi.cognify_journal.user.dto.UserRequestDto;
import com.aditi.cognify_journal.user.dto.UserResponseDto;
import com.aditi.cognify_journal.user.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}