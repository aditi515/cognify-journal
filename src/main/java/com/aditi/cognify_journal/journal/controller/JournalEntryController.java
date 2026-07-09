package com.aditi.cognify_journal.journal.controller;

import com.aditi.cognify_journal.journal.dto.JournalEntryRequestDto;
import com.aditi.cognify_journal.journal.dto.JournalEntryResponseDto;
import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.journal.mapper.JournalEntryMapper;
import com.aditi.cognify_journal.journal.service.JournalEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
@RequiredArgsConstructor
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntryResponseDto createEntry(@Valid @RequestBody JournalEntryRequestDto journalEntryRequestDto) {

        JournalEntry journalEntry = JournalEntryMapper.toEntity(journalEntryRequestDto);

        JournalEntry saveJournalEntry = journalEntryService.createEntry(journalEntry);

        return JournalEntryMapper.toResponseDto(saveJournalEntry);
    }


    @GetMapping("/{id}")
    public JournalEntryResponseDto getEntryById(@PathVariable Long id) {
        JournalEntry journalEntry = journalEntryService.getEntryById(id);
        return JournalEntryMapper.toResponseDto(journalEntry);
    }

    @PutMapping("/{id}")
    public JournalEntryResponseDto updateEntry(@PathVariable Long id,
                                               @Valid @RequestBody JournalEntryRequestDto journalEntryRequestDto) {
        JournalEntry journalEntry = JournalEntryMapper.toEntity(journalEntryRequestDto);
        JournalEntry updatedJournalEntry = journalEntryService.updateEntry(id, journalEntry);
        return JournalEntryMapper.toResponseDto(updatedJournalEntry);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        journalEntryService.deleteEntry(id);
    }
}