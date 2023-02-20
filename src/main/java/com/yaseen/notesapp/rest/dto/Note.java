package com.yaseen.notesapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    long noteId;
    private String title;
    private String body;

    private Set<String> tags;

    private Set<String> keyWords;

    @Builder.Default
    private boolean privateNode = Boolean.FALSE;
}
