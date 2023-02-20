package com.yaseen.notesapp.repo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UsersAccounts", uniqueConstraints=
@UniqueConstraint(columnNames={"email"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name="user_seq",sequenceName="user_seq_gen", allocationSize=1)
    private long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String secret;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;


    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
