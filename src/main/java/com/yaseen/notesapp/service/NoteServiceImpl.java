package com.yaseen.notesapp.service;

import com.yaseen.notesapp.exception.ModifyPublicNoteIsNotAllowedException;
import com.yaseen.notesapp.exception.NoteNotFoundException;
import com.yaseen.notesapp.repo.NoteEntityRepo;
import com.yaseen.notesapp.repo.entity.NoteEntity;
import com.yaseen.notesapp.rest.dto.Note;
import com.yaseen.notesapp.rest.dto.NotesSearchRequest;
import com.yaseen.notesapp.rest.dto.NotesSortBy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteEntityRepo repo;

    @Override
    public Page<Note> findUserNotes(long userId, NotesSearchRequest notesSearchRequest) {
        return repo.findAll(buildQuery(userId,notesSearchRequest), toPageRequest(notesSearchRequest))
                .map(NoteServiceImpl::mapToModel);
    }

    @Override
    public void deleteUserNote(long userId, long noteId) {
        getUserNote(userId, noteId)
                .filter(validateNoteForModification)
                .ifPresentOrElse(userNote -> repo.deleteById(userNote.getNoteId()),
                        ()-> new NoteNotFoundException("Note_Not_found"));
    }

    @Override
    public Note addUserNote(long userId, Note note) {
        NoteEntity save = repo.save(mapToEntity(userId, note));
        return mapToModel(save);
    }

    @Override
    public Note updateUserNote(long userId, Note note) {
        return getUserNote(userId, note.getNoteId())
                .filter(validateNoteForModification)
                .map(noteEntity -> repo.save(mapToEntity(userId, note)))
                .map(NoteServiceImpl::mapToModel)
                .orElseThrow(() ->new NoteNotFoundException("Note_Not_found"));
    }

    @Override
    public Optional<Note> getUserNote(long userId, long noteId) {
        return repo.findOneByUserIdAndId(userId, noteId)
                .map(NoteServiceImpl::mapToModel);
    }

    private static Predicate<Note> validateNoteForModification = (note) -> {
      if(!note.isPrivateNode()) {
          throw new ModifyPublicNoteIsNotAllowedException();
      }
      return true;
    };

    private static NoteEntity mapToEntity(long userId, Note note) {
        return NoteEntity.builder()
                .title(note.getTitle())
                .body(note.getBody())
                .tags(note.getTags() == null ? Collections.emptyList() : new ArrayList<>(note.getTags()))
                .userId(userId)
                .keyWords(note.getKeyWords() == null ? Collections.emptyList() : new ArrayList<>(note.getKeyWords()))
                .build();
    }

    private static Note mapToModel(NoteEntity save) {
        return Note.builder()
                .title(save.getTitle())
                .body(save.getBody())
                .noteId(save.getId())
                .keyWords(new HashSet<>(save.getKeyWords()))
                .privateNode(save.getPrivateNote())
                .tags(new HashSet<>(save.getTags()))
                .build();
    }
    private static Pageable toPageRequest(NotesSearchRequest req) {
        return PageRequest.of(req.getPage(), req.getSize(), req.getDirection(),getSortBy(req.getSortBy()));
    }

    private static String getSortBy(NotesSortBy sortBy) {
        return switch (sortBy){
            case CREATION_DATE -> NoteEntity.Fields.createdAt;
            default -> NoteEntity.Fields.createdAt;
        };
    }

    private static Specification<NoteEntity> buildQuery(long userId,NotesSearchRequest request) {
        Specification<NoteEntity> query = Specification.where((note, cq, cb) -> cb.equal(note.get("userId"), userId));
        if(StringUtils.hasText(request.getTag())) {
            query = query.and((note, cq, cb) -> cb.equal(note.get("tags"), request.getTag()));
        }
        if(StringUtils.hasText(request.getKeyWord())) {
            query =query.and((note, cq, cb) -> cb.equal(note.get("keywords"), request.getKeyWord()));
        }
        return query;
    }
}
