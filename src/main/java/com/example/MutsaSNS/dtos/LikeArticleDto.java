package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.LikeArticleEntity;
import lombok.Data;

@Data
public class LikeArticleDto {
    private Long id;
    private Long article;
    private String writer;

    public static LikeArticleDto fromEntity(LikeArticleEntity likeArticleEntity) {
        LikeArticleDto likeArticleDto = new LikeArticleDto();
        likeArticleDto.setId(likeArticleEntity.getId());
        likeArticleDto.setArticle(likeArticleEntity.getArticle().getId());
        likeArticleDto.setWriter(likeArticleEntity.getWriter().getUsername());
        return likeArticleDto;
    }
}
