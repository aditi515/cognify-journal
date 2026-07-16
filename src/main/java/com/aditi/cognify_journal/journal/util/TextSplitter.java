package com.aditi.cognify_journal.journal.util;

import java.util.ArrayList;
import java.util.List;

public class TextSplitter {
    private static final int MAX_CHUNK_SIZE = 500; // Max characters per chunk
    private static final int OVERLAP_SIZE = 50;    // Overlap to keep semantic context intact

    public static List<String> splitText(String text) {
        List<String> chunks = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return chunks;
        }

        int length = text.length();
        int start = 0;

        while (start < length) {
            int end = Math.min(start + MAX_CHUNK_SIZE, length);

            // If we are not at the end of the text, try to split at a space to avoid cutting words
            if (end < length) {
                int lastSpace = text.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }

            chunks.add(text.substring(start, end).trim());

            // Move start forward, subtracting overlap to maintain continuity between chunks
            start = end - OVERLAP_SIZE;
            if (start < 0 || start >= length - OVERLAP_SIZE) {
                start = end; // Fallback if overlap pushes us backward endlessly
            }
        }

        return chunks;
    }
}
