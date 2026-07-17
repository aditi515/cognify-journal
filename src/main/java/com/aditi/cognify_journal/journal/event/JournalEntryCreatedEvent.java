package com.aditi.cognify_journal.journal.event;

import com.aditi.cognify_journal.journal.entity.JournalEntry;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class JournalEntryCreatedEvent extends ApplicationEvent {
    private final JournalEntry journalEntry;

    public JournalEntryCreatedEvent(Object source, JournalEntry journalEntry) {
        super(source); // Passes the publishing class reference to Spring
        this.journalEntry = journalEntry; // Holds the exact database payload
    }
}
