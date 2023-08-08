package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.ArticleEntity;
import com.example.MutsaSNS.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<List<ArticleEntity>> findAllByWriterOrderByCreatedAtDesc(UserEntity username);
}
