package com.aditi.cognify_journal.journal.repository;

import com.aditi.cognify_journal.journal.entity.JournalChunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JournalChunkRepository extends JpaRepository<JournalChunk, Long> {
    @Query(value = """
    SELECT jc.* FROM journal_chunks jc
    INNER JOIN journal_entries je ON jc.journal_entry_id = je.id
    WHERE je.user_id = :userId
    ORDER BY jc.embedding <=> CAST(:queryEmbedding AS vector) ASC
    LIMIT :limit
    """, nativeQuery = true)
    List<JournalChunk> findTopSimilarChunksForUser(
            @Param("queryEmbedding") float[] queryEmbedding,
            @Param("userId") Long userId,
            @Param("limit") int limit
    );
}
