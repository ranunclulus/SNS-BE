package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.ArticleDto;
import com.example.MutsaSNS.dtos.CommentDto;
import com.example.MutsaSNS.dtos.ResponseDto;
import com.example.MutsaSNS.jwt.JwtTokenUtils;
import com.example.MutsaSNS.service.ArticleService;
import com.example.MutsaSNS.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
    private final JwtTokenUtils jwtTokenUtils;
    private final CommentService commentService;

    // 피드 작성 API
    @PostMapping()
    public ResponseDto createArticle(@RequestBody ArticleDto articleDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            articleService.createArticle(articleDto);
            responseDto.getResponse().put("message", "새로운 게시글이 등록되었습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        } catch (IOException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 이미지 업로드 API
    @PutMapping(value = "/{articleId}/image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadArticleImg(
            HttpServletRequest request,
            @RequestParam("photo")MultipartFile multipartFile,
            @PathVariable("articleId") Long articleId) throws IOException {
        ResponseDto responseDto = new ResponseDto();
        try {
            articleService.uploadArticleImg(articleId, multipartFile);
            responseDto.getResponse().put("message", "게시글 사진을 업로드했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 이미지 삭제 API
    @DeleteMapping("/{articleId}/image/{imageId}")
    public ResponseDto deleteArticleImg(
            @PathVariable("articleId") Long articleId,
            @PathVariable("imageId") Long imageId)
    {
        ResponseDto responseDto = new ResponseDto();
        try {
            articleService.deleteArticleImg(articleId, imageId);
            responseDto.getResponse().put("message", "게시글 사진을 삭제했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }


    // 게시글 목록 조회 API
    @GetMapping()
    public ResponseDto readArticleByUser(HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        try {
            List<ArticleDto> articleDtos = articleService.readArticleByUser(username);
            if (articleDtos.size() == 0) responseDto.getResponse().put("message", "작성된 게시글이 없습니다");
            else {
                responseDto.getResponse().put("result", articleDtos);
                responseDto.getResponse().put("message", "게시글 목록을 불러오는 데 성공했습니다");
            }
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }

        return responseDto;
    }

    // 게시글 하나 조회 API
    @GetMapping("/{articleId}")
    public ResponseDto readArticle(@PathVariable("articleId") Long articleId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            ArticleDto articleDto = articleService.readArticle(articleId);
            List<CommentDto> commentDtos = commentService.readAllComments(articleId);
            responseDto.getResponse().put("result", articleDto);
            responseDto.getResponse().put("comments", commentDtos);
            responseDto.getResponse().put("message", "게시글을 불러오는 데 성공했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 게시글 삭제 API
    @DeleteMapping("/{articleId}")
    public ResponseDto deleteArticle(@PathVariable("articleId") Long articleId) {
        ResponseDto responseDto = new ResponseDto();
        try {
            articleService.deleteArticle(articleId);
            responseDto.getResponse().put("message", "게시글을 성공적으로 삭제했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }
}
