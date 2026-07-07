package com.aditi.cognify_journal.journal.service;

import com.aditi.cognify_journal.journal.entity.JournalEntry;

import java.util.List;

public interface JournalEntryService {

    JournalEntry createEntry(JournalEntry entry);

    List<JournalEntry> getMyEntries();

    JournalEntry getEntryById(Long id);

    JournalEntry updateEntry(Long id, JournalEntry entry);

    void deleteEntry(Long id);
}