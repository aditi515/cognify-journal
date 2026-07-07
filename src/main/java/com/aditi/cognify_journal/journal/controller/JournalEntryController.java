package com.aditi.cognify_journal.journal.controller;

import com.aditi.cognify_journal.journal.entity.JournalEntry;
import com.aditi.cognify_journal.journal.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
@RequiredArgsConstructor
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        return journalEntryService.createEntry(entry);
    }

    @GetMapping
    public List<JournalEntry> getMyEntries() {
        return journalEntryService.getMyEntries();
    }

    @GetMapping("/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return journalEntryService.getEntryById(id);
    }

    @PutMapping("/{id}")
    public JournalEntry updateEntry(@PathVariable Long id,
                                    @RequestBody JournalEntry entry) {
        return journalEntryService.updateEntry(id, entry);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        journalEntryService.deleteEntry(id);
    }
}