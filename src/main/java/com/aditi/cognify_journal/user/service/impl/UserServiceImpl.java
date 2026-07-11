package com.aditi.cognify_journal.user.service.impl;

import com.aditi.cognify_journal.exception.ResourceNotFoundException;
import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.user.entity.User;
import com.aditi.cognify_journal.user.repository.UserRepository;
import com.aditi.cognify_journal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<JournalEntry> getMyJournalEntries() {
        User currentUser = getCurrentUser();

        return currentUser.getJournalEntries();
    }

    @Override
    public User updateProfile(User user) {
        User currentUser = getCurrentUser();

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());

        return userRepository.save(currentUser);
    }

    @Override
    public void deleteAccount() {
        User currentUser = getCurrentUser();

        userRepository.delete(currentUser);
    }
}