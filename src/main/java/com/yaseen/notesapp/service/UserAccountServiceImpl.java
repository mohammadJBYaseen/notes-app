package com.yaseen.notesapp.service;

import com.yaseen.notesapp.model.UserAccount;
import com.yaseen.notesapp.repo.UserAccountRepo;
import com.yaseen.notesapp.repo.entity.UserAccountEntity;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepo repo;

    @Override
    public Optional<UserAccount> findAccountByEmail(String email) {
        return repo.findOneByEmail(email)
                .map(acc -> UserAccount.builder()
                        .email(acc.getEmail())
                        .firstName(acc.getFirstName())
                        .lastName(acc.getLastName())
                        .roles(acc.getRoles())
                        .secret(acc.getSecret())
                        .id(acc.getId())
                        .build());
    }

    @Override
    public Optional<UserAccount> findAccountById(@NotNull Long id) {
        return repo.findById(id)
                .map(acc -> UserAccount.builder()
                        .email(acc.getEmail())
                        .firstName(acc.getFirstName())
                        .lastName(acc.getLastName())
                        .roles(acc.getRoles())
                        .id(acc.getId())
                        .build());
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        return repo.findById(userAccount.getId())
                .map(acc -> repo.save(UserAccountEntity.builder()
                        .email(userAccount.getEmail())
                        .firstName(userAccount.getFirstName())
                        .lastName(userAccount.getLastName())
                        .roles(userAccount.getRoles())
                        .id(acc.getId())
                        .build()))
                .map(acc -> UserAccount.builder()
                        .email(acc.getEmail())
                        .firstName(acc.getFirstName())
                        .lastName(acc.getLastName())
                        .roles(acc.getRoles())
                        .id(acc.getId())
                        .build())
                .orElseThrow(()-> new UsernameNotFoundException("User_Not_Found"));
    }

    @Override
    @Transactional
    public UserAccount save(UserAccount userAccount) {
        UserAccountEntity acc = repo.save(UserAccountEntity.builder()
                .email(userAccount.getEmail())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .roles(userAccount.getRoles())
                .secret(userAccount.getSecret())
                .build());
        return UserAccount.builder()
                .email(acc.getEmail())
                .firstName(acc.getFirstName())
                .lastName(acc.getLastName())
                .roles(acc.getRoles())
                .id(acc.getId())
                .build();
    }

    @Override
    public void delete(@NotNull Long id) {
        repo.findById(id)
                .ifPresentOrElse(repo::delete,()-> {throw new UsernameNotFoundException("User_Not_Found");});
    }
}
