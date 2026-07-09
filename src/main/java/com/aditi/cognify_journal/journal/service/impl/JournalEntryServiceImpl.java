package com.aditi.cognify_journal.journal.service.impl;

import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.journal.repository.JournalEntryRepository;
import com.aditi.cognify_journal.journal.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalEntryServiceImpl implements JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    @Override
    public JournalEntry createEntry(JournalEntry entry) {
        return journalEntryRepository.save(entry);
    }

    @Override
    public JournalEntry getEntryById(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public JournalEntry updateEntry(Long id, JournalEntry entry) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteEntry(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}