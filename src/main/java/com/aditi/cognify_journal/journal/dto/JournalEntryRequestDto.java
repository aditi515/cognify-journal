package com.aditi.cognify_journal.journal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntryRequestDto {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    private String content;
}