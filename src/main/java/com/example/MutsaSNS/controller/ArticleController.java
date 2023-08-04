package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.ArticleDto;
import com.example.MutsaSNS.dtos.ResponseDto;
import com.example.MutsaSNS.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 피드 작성 API
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, "application/json"})
    public ResponseDto createArticle(
            @RequestBody Optional<List<MultipartFile>> images,
            @RequestBody ArticleDto articleDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            articleService.createArticle(images, articleDto);
            responseDto.getResponse().put("message", "새로운 게시글이 등록되었습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        } catch (IOException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }
}
