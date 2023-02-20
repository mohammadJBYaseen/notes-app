package com.yaseen.notesapp.model;

import com.yaseen.notesapp.repo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAccount {

    private long id;
    private String email;

    private String secret;

    private String firstName;
    private String lastName;

    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
