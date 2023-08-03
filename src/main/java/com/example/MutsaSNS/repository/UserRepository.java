package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
