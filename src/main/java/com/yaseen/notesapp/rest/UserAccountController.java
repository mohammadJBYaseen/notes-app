package com.yaseen.notesapp.rest;

import com.yaseen.notesapp.configuration.JwtTokenUtil;
import com.yaseen.notesapp.model.UserAccount;
import com.yaseen.notesapp.repo.entity.Role;
import com.yaseen.notesapp.rest.dto.JwtRequest;
import com.yaseen.notesapp.rest.dto.JwtResponse;
import com.yaseen.notesapp.rest.dto.UserRegistrationDto;
import com.yaseen.notesapp.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserAccountController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    private final UserAccountService userAccountService;

    private final PasswordEncoder encoder;


    @PostMapping(value = "/authenticate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JwtResponse createAuthenticationToken(@ModelAttribute JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getSecret());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createUser(@Valid @ModelAttribute  UserRegistrationDto userRegistrationDto) throws Exception {

        userAccountService.save(UserAccount.builder()
                        .firstName(userRegistrationDto.getFirstname())
                        .lastName(userRegistrationDto.getLastname())
                        .email(userRegistrationDto.getEmail())
                        .roles(Collections.singleton(Role.USER))
                        .secret(encoder.encode(new String(userRegistrationDto.getPassKey())))
                .build());
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, char[] password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, new String(password)));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
