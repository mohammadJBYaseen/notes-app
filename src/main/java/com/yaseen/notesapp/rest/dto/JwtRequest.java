package com.yaseen.notesapp.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
public class JwtRequest {

    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "invalid email format")
    @NotEmpty(message = "username is required")
    private String username;
    @NotEmpty(message = "password is required")
    private char[] secret;
}
