package com.aditi.cognify_journal.user.service.impl;

import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.user.entity.User;
import com.aditi.cognify_journal.user.repository.UserRepository;
import com.aditi.cognify_journal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<JournalEntry> getMyJournalEntries() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public User updateProfile(User user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteAccount() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}