package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.ArticleImagesEntity;
import lombok.Data;

@Data
public class ArticleImagesDto {
    private Long id;
    private Long article;
    private String imageUrl;
    private boolean thumnail;

    public static ArticleImagesDto fromEntity(ArticleImagesEntity articleImagesEntity) {
        ArticleImagesDto articleImagesDto = new ArticleImagesDto();
        articleImagesDto.setId(articleImagesEntity.getId());
        articleImagesDto.setArticle(articleImagesEntity.getArticle().getId());
        articleImagesDto.setImageUrl(articleImagesEntity.getImageUrl());
        articleImagesDto.setThumnail(articleImagesEntity.isThumnail());
        return articleImagesDto;
    }
}
