package com.aditi.cognify_journal.journal.repository;

import com.aditi.cognify_journal.journal.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}
