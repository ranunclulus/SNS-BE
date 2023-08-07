package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String writer;
    private String content;

    public static CommentDto fromEntity(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setWriter(commentEntity.getWriter().getUsername());
        commentDto.setContent(commentEntity.getContent());
        return commentDto;
    }
}
