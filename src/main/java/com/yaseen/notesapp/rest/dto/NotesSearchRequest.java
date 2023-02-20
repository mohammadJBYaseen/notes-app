package com.yaseen.notesapp.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Sort;

import java.util.Set;

@Jacksonized
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotesSearchRequest {
    @Builder.Default
    @NotNull
    private NotesSortBy sortBy = NotesSortBy.CREATION_DATE;

    @Builder.Default
    @NotNull
    private Sort.Direction direction = Sort.Direction.DESC;

    private String tag;

    private String keyWord;

    @Builder.Default
    @PositiveOrZero
    private int page = 0;


    @Builder.Default
    @Range(min = 10,max = 100)
    private int size =10;

}
