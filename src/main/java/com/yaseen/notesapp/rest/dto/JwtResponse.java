package com.yaseen.notesapp.rest.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
}
