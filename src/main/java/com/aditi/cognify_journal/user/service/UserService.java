package com.aditi.cognify_journal.user.service;

import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.user.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    User getCurrentUser();

    List<JournalEntry> getMyJournalEntries();

    User updateProfile(User user);

    void deleteAccount();
}