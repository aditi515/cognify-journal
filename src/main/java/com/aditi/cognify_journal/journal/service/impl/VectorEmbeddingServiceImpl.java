package com.aditi.cognify_journal.journal.service.impl;

import com.aditi.cognify_journal.journal.service.VectorEmbeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VectorEmbeddingServiceImpl implements VectorEmbeddingService {
    private final EmbeddingModel embeddingModel;

    @Override
    public float[] generateEmbedding(String text) {
        if (text == null || text.isBlank()) {
            return new float[0];
        }
        try {
            log.info("Generating embedding for text chunk of length: {}", text.length());
            return embeddingModel.embed(text);
        } catch (Exception e) {
            log.error("Failed to generate vector embedding via Spring AI", e);
            throw new RuntimeException("AI Embedding generation failed: " + e.getMessage(), e);
        }
    }
}
