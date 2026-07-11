package com.aditi.cognify_journal.journal.service.impl;

import com.aditi.cognify_journal.exception.ResourceNotFoundException;
import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.journal.repository.JournalEntryRepository;
import com.aditi.cognify_journal.journal.service.JournalEntryService;
import com.aditi.cognify_journal.user.entity.User;
import com.aditi.cognify_journal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalEntryServiceImpl implements JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    @Override
    public JournalEntry createEntry(JournalEntry journalEntry) {
        User currentUser = userService.getCurrentUser();

        journalEntry.setUser(currentUser);

        currentUser.getJournalEntries().add(journalEntry);
        return journalEntryRepository.save(journalEntry);
    }

    @Override
    public JournalEntry getEntryById(Long id) {
        User currentUser = userService.getCurrentUser();

        JournalEntry journalEntry = journalEntryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Journal entry not found"));

        if (!journalEntry.getUser().getId().equals(currentUser.getId())) {
            throw new ResourceNotFoundException("Journal entry not found for the current user.");
        }

        return journalEntry;
    }

    @Override
    public JournalEntry updateEntry(Long id, JournalEntry journalEntry) {
        JournalEntry existingEntry = getEntryById(id);

        existingEntry.setTitle(journalEntry.getTitle());
        existingEntry.setContent(journalEntry.getContent());

        return journalEntryRepository.save(existingEntry);
    }

    @Override
    public void deleteEntry(Long id) {
        JournalEntry journalEntry = getEntryById(id);

        journalEntryRepository.delete(journalEntry);
    }
}