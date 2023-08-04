package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private boolean draft;
    private List<String> imageUrl;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;

    public static ArticleDto fromEntity(ArticleEntity articleEntity) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(articleEntity.getId());
        articleDto.setWriter(articleEntity.getWriter().getUsername());
        articleDto.setTitle(articleEntity.getTitle());
        articleDto.setContent(articleEntity.getContent());
        articleDto.setDraft(articleEntity.isDraft());
        articleDto.setImageUrl(new ArrayList<>());
        for(ArticleImagesEntity articleImagesEntity: articleEntity.getImages()) {
            articleDto.getImageUrl().add(articleImagesEntity.getImageUrl());
        }
        articleDto.setDeletedAt(articleEntity.getDeletedAt());
        articleDto.setCreatedAt(articleEntity.getCreatedAt());
        return articleDto;
    }
 }
