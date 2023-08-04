package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
