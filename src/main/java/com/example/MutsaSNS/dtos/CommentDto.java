package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String writer;
    private Long article;
    private String content;

    public static CommentDto fromEntity(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setWriter(commentEntity.getWriter().getUsername());
        commentDto.setArticle(commentEntity.getArticle().getId());
        commentDto.setContent(commentEntity.getContent());
        return commentDto;
    }
}
