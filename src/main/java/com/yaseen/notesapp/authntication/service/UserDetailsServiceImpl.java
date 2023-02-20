package com.yaseen.notesapp.authntication.service;

import com.yaseen.notesapp.model.UserAccount;
import com.yaseen.notesapp.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountService.findAccountByEmail(email)
                .map(account -> new User(account.getEmail(),account.getSecret(),
                        account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
                                .collect(Collectors.toSet())))
                .orElseThrow(() -> new UsernameNotFoundException("UserNotFound Exception"));
    }
}
