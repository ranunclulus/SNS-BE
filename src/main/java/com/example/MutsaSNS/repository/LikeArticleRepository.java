package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.LikeArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeArticleRepository extends JpaRepository<LikeArticleEntity, Long> {
    boolean existsByArticle_IdAndWriter_Username(Long articleId, String username);
    Optional<LikeArticleEntity> findByArticle_IdAndWriter_Username(Long articleId, String username);
}
