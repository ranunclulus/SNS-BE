package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.CommentDto;
import com.example.MutsaSNS.dtos.ResponseDto;
import com.example.MutsaSNS.service.ArticleService;
import com.example.MutsaSNS.service.CommentService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/articles/{articleId}/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public ResponseDto createComment(
            @PathVariable("articleId") Long articleId,
            @RequestBody CommentDto commentDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            commentService.createComment(articleId, commentDto);
            responseDto.getResponse().put("message", "새로운 댓글이 등록되었습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    @PutMapping("/{commentId}")
    public ResponseDto updateComment(
            @PathVariable("articleId") Long articleId,
            @RequestBody CommentDto commentDto,
            @PathVariable("commentId") Long commentId
    ) {
        ResponseDto responseDto = new ResponseDto();
        try {
            commentService.updateComment(articleId, commentDto, commentId);
            responseDto.getResponse().put("message", "댓글이 수정되었습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }
}
