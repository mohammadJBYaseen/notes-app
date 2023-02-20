package com.yaseen.notesapp.repo;

import com.yaseen.notesapp.repo.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccountEntity,Long> {

    Optional<UserAccountEntity> findOneByEmail(String email);

}
