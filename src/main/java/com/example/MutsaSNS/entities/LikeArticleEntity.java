package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "like_article")
public class LikeArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article")
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "writer")
    private UserEntity writer;
}
