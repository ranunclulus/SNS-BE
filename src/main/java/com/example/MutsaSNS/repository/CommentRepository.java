package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByArticle_Id(Long articleId);
}
