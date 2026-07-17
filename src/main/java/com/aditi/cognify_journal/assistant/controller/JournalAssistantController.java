package com.aditi.cognify_journal.assistant.controller;

import com.aditi.cognify_journal.assistant.dto.AssistantRequestDto;
import com.aditi.cognify_journal.assistant.dto.AssistantResponseDto;
import com.aditi.cognify_journal.assistant.service.JournalAssistantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
@Slf4j
public class JournalAssistantController {
    private final JournalAssistantService journalAssistantService;
    @PostMapping("/query")
    public ResponseEntity<AssistantResponseDto> askAssistant(@RequestBody AssistantRequestDto requestDto) {
        log.info("REST request received to process AI assistant insight query.");

        // 1. Delegate the processing to the service layer using raw text
        String aiRawAnswer = journalAssistantService.answerQuery(requestDto.getQuery());

        // 2. Wrap the text output cleanly inside the promised Response DTO layer
        AssistantResponseDto responseDto = AssistantResponseDto.builder()
                .answer(aiRawAnswer)
                .build();

        return ResponseEntity.ok(responseDto);
    }
}
