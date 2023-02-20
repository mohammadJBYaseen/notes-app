package com.yaseen.notesapp.repo;

import com.yaseen.notesapp.repo.entity.NoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteEntityRepo extends JpaRepository<NoteEntity,Long>, JpaSpecificationExecutor<NoteEntity> {

    Page<NoteEntity> findByUserId(Long userId, Pageable pageable);

    Optional<NoteEntity> findOneByUserIdAndId(long userId, long noteId);
}
