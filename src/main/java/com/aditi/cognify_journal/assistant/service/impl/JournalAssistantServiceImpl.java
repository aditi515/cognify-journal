package com.aditi.cognify_journal.assistant.service.impl;

import com.aditi.cognify_journal.assistant.service.JournalAssistantService;
import com.aditi.cognify_journal.journal.entity.JournalChunk;
import com.aditi.cognify_journal.journal.repository.JournalChunkRepository;
import com.aditi.cognify_journal.journal.service.VectorEmbeddingService;
import com.aditi.cognify_journal.user.entity.User;
import com.aditi.cognify_journal.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalAssistantServiceImpl implements JournalAssistantService {
    private final JournalChunkRepository journalChunkRepository;
    private final VectorEmbeddingService vectorEmbeddingService;
    private final UserService userService;
    private final RestTemplate restTemplate; // Used for hitting your local Ollama instance directly

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    @Override
    public String answerQuery(String query) {
        User currentUser = userService.getCurrentUser();
        Long currentUserId = currentUser.getId();


        float[] queryEmbedding = vectorEmbeddingService.generateEmbedding(query);


        List<JournalChunk> similarChunks = journalChunkRepository.findTopSimilarChunksForUser(queryEmbedding, currentUserId, 5);
        log.info("Retrieved {} relevant journal historical chunks for analysis.", similarChunks.size());

        // Extract and combine the text blocks into a clean context pool
        String contextText = similarChunks.stream()
                .map(JournalChunk::getContent)
                .collect(Collectors.joining("\n---\n"));

        //  Construct a clear, strict instruction prompt for your local LLM
        String engineeredPrompt = String.format("""
            You are a secure, strictly analytical AI journal analysis engine.
            Your role is to evaluate the provided journal context and provide data-driven insights.
            Do not offer emotional counseling, validation, or tell the user how they should feel or behave.
            
            [CONTEXT START]
            %s
            [CONTEXT END]
            
            Instruction: Based ONLY on the facts and text explicitly written in the context above, answer the user's question. 
            Provide an objective analysis of patterns found in the text. Do not extrapolate, guess, speculate, or introduce external knowledge.
            If the context does not contain direct evidence to answer the query, reply exactly with: "I could not find relevant patterns in your historical entries to answer this question."
            
            User Question: %s
            """, contextText, query);

        // Execute direct REST payload call to Ollama generation engine
        return callOllamaGenerationEngine(engineeredPrompt);
    }

    private String callOllamaGenerationEngine(String prompt) {
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "llama3"); // Ensure this matches your local model name
            requestBody.put("prompt", prompt);
            requestBody.put("stream", false); // We want a full, single JSON response back

            log.debug("Sending context prompt payload to local Ollama container...");

            // Invoke the REST client endpoint
            Map<String, Object> response = restTemplate.postForObject(OLLAMA_URL, requestBody, Map.class);

            if (response != null && response.containsKey("response")) {
                return (String) response.get("response");
            }

            throw new RuntimeException("Malformed response format received from local LLM engine.");

        } catch (Exception e) {
            log.error("Failed to generate contextual AI response from local Ollama engine", e);
            return "I'm sorry, I'm having trouble connecting to my local AI thinking engine right now. Please try again shortly!";
        }

    }
}
