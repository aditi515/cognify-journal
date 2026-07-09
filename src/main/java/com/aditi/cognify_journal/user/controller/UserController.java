package com.aditi.cognify_journal.user.controller;

import com.aditi.cognify_journal.journal.dto.JournalEntryResponseDto;
import com.aditi.cognify_journal.journal.mapper.JournalEntryMapper;
import com.aditi.cognify_journal.user.dto.UserRequestDto;
import com.aditi.cognify_journal.user.dto.UserResponseDto;
import com.aditi.cognify_journal.user.entity.User;
import com.aditi.cognify_journal.user.mapper.UserMapper;
import com.aditi.cognify_journal.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDto registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        User user = UserMapper.toEntity(userRequestDto);

        User savedUser = userService.registerUser(user);

        return UserMapper.toResponseDto(savedUser);

    }

    @GetMapping("/me")
    public UserResponseDto getCurrentUser() {
        User user = userService.getCurrentUser();
        return UserMapper.toResponseDto(user);
    }

    @GetMapping("/me/journals")
    public List<JournalEntryResponseDto> getMyJournalEntries() {

        return userService.getMyJournalEntries()
                .stream()
                .map(JournalEntryMapper::toResponseDto)
                .toList();
    }

    @PutMapping("/me")
    public UserResponseDto updateProfile(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = UserMapper.toEntity(userRequestDto);

        User updatedUser = userService.updateProfile(user);

        return UserMapper.toResponseDto(updatedUser);
    }

    @DeleteMapping("/me")
    public void deleteAccount() {
        userService.deleteAccount();
    }
}