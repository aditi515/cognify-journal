package com.aditi.cognify_journal.journal.service.impl;

import com.aditi.cognify_journal.journal.entity.JournalChunk;
import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.journal.event.JournalEntryCreatedEvent;
import com.aditi.cognify_journal.journal.repository.JournalChunkRepository;
import com.aditi.cognify_journal.journal.service.VectorEmbeddingService;
import com.aditi.cognify_journal.journal.util.TextSplitter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JournalEventListener {

    private final JournalChunkRepository journalChunkRepository;
    private final VectorEmbeddingService vectorEmbeddingService;

    @Async // 🚀 Offloads execution entirely onto a background thread pool
    @EventListener
    public void handleJournalEntryCreated(JournalEntryCreatedEvent event) {
        JournalEntry savedEntry = event.getJournalEntry();
        log.info("Starting background processing of vector embeddings for Journal Entry ID: {}", savedEntry.getId());

        try {
            // Slice the text content into smaller sections
            List<String> textChunks = TextSplitter.splitText(savedEntry.getContent());
            List<JournalChunk> chunksToSave = new ArrayList<>();

            // Process text blocks, call Ollama HTTP engine, and generate embeddings
            for (int i = 0; i < textChunks.size(); i++) {
                String chunkText = textChunks.get(i);

                // Heavy HTTP REST invocation to your local AI model container
                float[] vector = vectorEmbeddingService.generateEmbedding(chunkText);

                JournalChunk chunk = JournalChunk.builder()
                        .journalEntry(savedEntry)
                        .content(chunkText)
                        .chunkIndex(i)
                        .embedding(vector)
                        .build();

                chunksToSave.add(chunk);
            }

            // Persistence layer execution in the background
            journalChunkRepository.saveAll(chunksToSave);
            log.info("Successfully generated and stored {} vector chunks for Journal Entry ID: {}", chunksToSave.size(), savedEntry.getId());

        } catch (Exception e) {
            log.error("Critical failure during background vector ingestion for journal entry ID: {}", savedEntry.getId(), e);
            // This prevents app crashes if Dockerized Ollama times out or goes offline
        }
    }
}
