package com.yaseen.notesapp.service;

import com.yaseen.notesapp.rest.dto.Note;
import com.yaseen.notesapp.rest.dto.NotesSearchRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface NoteService {

    Page<Note> findUserNotes(long userId, NotesSearchRequest notesSearchRequest);
    void deleteUserNote(long userId, long noteId);

    Note addUserNote(long userId, Note note);

    Note updateUserNote(long userId, Note note);

    Optional<Note> getUserNote(long userId, long noteId);

}
