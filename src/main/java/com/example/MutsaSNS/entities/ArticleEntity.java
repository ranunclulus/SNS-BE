package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "writer")
    @ManyToOne
    private UserEntity writer;

    @Column(nullable = false)
    private String title;

    private String content;
    private boolean draft;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "article")
    private List<ArticleImagesEntity> images;

    @OneToMany(mappedBy = "article")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "article")
    private List<LikeArticleEntity> likes;
}
