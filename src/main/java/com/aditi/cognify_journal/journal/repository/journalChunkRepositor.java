package com.aditi.cognify_journal.journal.repository;

import com.aditi.cognify_journal.journal.entity.JournalChunk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface journalChunkRepositor extends JpaRepository<JournalChunk, Long> {
}
