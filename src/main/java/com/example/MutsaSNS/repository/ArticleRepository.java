package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
