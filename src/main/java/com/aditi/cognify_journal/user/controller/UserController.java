package com.aditi.cognify_journal.user.controller;

import com.aditi.cognify_journal.user.entity.User;
import com.aditi.cognify_journal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/me")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PutMapping("/me")
    public User updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }

    @DeleteMapping("/me")
    public void deleteAccount() {
        userService.deleteAccount();
    }
}