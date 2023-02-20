package com.yaseen.notesapp.rest;

import com.yaseen.notesapp.exception.NoteNotFoundException;
import com.yaseen.notesapp.rest.dto.Note;
import com.yaseen.notesapp.rest.dto.NotesSearchRequest;
import com.yaseen.notesapp.service.NoteService;
import com.yaseen.notesapp.service.UserAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final UserAccountService userAccountService;
    private final NoteService noteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public Page<Note> getUserNote(Principal principal, NotesSearchRequest request) {
        return userAccountService.findAccountByEmail(principal.getName())
                .map(user -> noteService.findUserNotes(user.getId(), request))
                .orElseThrow(() -> new UsernameNotFoundException("User_Not_found"));
    }


    @GetMapping(value = "/{note-id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public Note viewNote(Principal principal, @PathVariable("note-id")@NotNull Long noteId) {
        return userAccountService.findAccountByEmail(principal.getName())
                .map(user -> noteService.getUserNote(user.getId(), noteId))
                .map(note -> note
                        .orElseThrow(() -> new NoteNotFoundException("Note_Not_Found")) )
                .orElseThrow(() -> new UsernameNotFoundException("User_Not_found"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public Note createNote(Principal principal, @Valid @RequestBody Note note) {
        return userAccountService.findAccountByEmail(principal.getName())
                .map(user -> noteService.addUserNote(user.getId(), note))
                .orElseThrow(() -> new UsernameNotFoundException("User_Not_found"));
    }


    @PutMapping(value = "/{note-id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public Note updateNote(Principal principal, @PathVariable("note-id")@NotNull Long noteId,@Valid @RequestBody Note note) {

        note.setNoteId(noteId);
        return userAccountService.findAccountByEmail(principal.getName())
                .map(user -> noteService.updateUserNote(user.getId(), note))
                .orElseThrow(() -> new UsernameNotFoundException("User_Not_found"));
    }

    @DeleteMapping(value = "/{note-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public void deleteNode(Principal principal, @PathVariable("note-id")@NotNull Long noteId) {
        userAccountService.findAccountByEmail(principal.getName())
                .ifPresent(user -> noteService.deleteUserNote(user.getId(), noteId));
    }


}
