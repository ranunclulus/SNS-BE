package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ArticleImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article")
    private ArticleEntity article;

    private String imageUrl;
    private boolean thumnail;
}
