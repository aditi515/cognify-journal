package com.aditi.cognify_journal.journal.mapper;

import com.aditi.cognify_journal.journal.dto.JournalEntryRequestDto;
import com.aditi.cognify_journal.journal.dto.JournalEntryResponseDto;
import com.aditi.cognify_journal.journal.entity.JournalEntry;

import java.util.stream.Collectors;

public class JournalEntryMapper {

    public static JournalEntry toEntity(JournalEntryRequestDto dto) {
        return JournalEntry.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public static JournalEntryResponseDto toResponseDto(JournalEntry entity) {
        return JournalEntryResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}