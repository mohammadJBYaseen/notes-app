package com.yaseen.notesapp.service;

import com.yaseen.notesapp.model.UserAccount;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccount> findAccountByEmail(@NotEmpty String email);
    Optional<UserAccount> findAccountById(@NotNull Long id);
    UserAccount update(@NotNull UserAccount userAccount);

    UserAccount save(@NotNull UserAccount userAccount);
    void delete(@NotNull Long userAccount);
}
