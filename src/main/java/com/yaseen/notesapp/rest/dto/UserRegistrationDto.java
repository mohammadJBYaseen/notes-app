package com.yaseen.notesapp.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Valid
@Jacksonized
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotEmpty
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "invalid email format")
    private String email;

    @NotEmpty(message = "firstname is required")
    private String firstname;

    @NotEmpty(message = "lastname is required")
    private String lastname;


    /**
     * Should contain at least a capital letter
     * Should contain at least a small letter
     * Should contain at least a number
     * Should contain at least a special character
     * And minimum length
     */
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*]).{8,}$", message = "weak password")
    private String passKey;

}
