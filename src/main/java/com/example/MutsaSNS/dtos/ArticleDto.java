package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.ArticleImagesEntity;
import com.example.MutsaSNS.entities.CommentEntity;
import com.example.MutsaSNS.entities.LikeArticleEntity;
import com.example.MutsaSNS.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDto {

    private Long id;
    private UserEntity writer;
    private String title;
    private String content;
    private boolean draft;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private List<ArticleImagesEntity> images;
    private List<CommentEntity> comments;
    private List<LikeArticleEntity> likes;
}
