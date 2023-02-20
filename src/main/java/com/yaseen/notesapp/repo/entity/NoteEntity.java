package com.yaseen.notesapp.repo.entity;

import com.yaseen.notesapp.repo.convertor.CommaSeparatedColumnWriteConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private Long userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    private List<String> tags;
    private List<String> keyWords;

    @NotNull
    private Boolean privateNote = Boolean.FALSE;

    @Positive
    @CreatedDate
    private long createdAt;
    @Positive
    @LastModifiedDate
    private long updatedAt;

    public boolean equals(Object obj) {
        if(!(obj instanceof NoteEntity)){
            return false;
        }
        return Objects.equals(getId(),((NoteEntity)obj).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
