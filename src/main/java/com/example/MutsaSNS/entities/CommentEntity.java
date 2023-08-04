package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer")
    private UserEntity writer;

    @ManyToOne
    @JoinColumn(name = "article")
    private ArticleEntity article;

    private String content;
}
