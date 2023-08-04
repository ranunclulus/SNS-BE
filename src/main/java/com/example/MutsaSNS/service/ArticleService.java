package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.ArticleDto;
import com.example.MutsaSNS.entities.ArticleEntity;
import com.example.MutsaSNS.entities.ArticleImagesEntity;
import com.example.MutsaSNS.exceptions.badRequest.TitleNullException;
import com.example.MutsaSNS.exceptions.badRequest.WriterNullException;
import com.example.MutsaSNS.exceptions.notFound.UsernameNotFoundException;
import com.example.MutsaSNS.repository.ArticleImagesRepository;
import com.example.MutsaSNS.repository.ArticleRepository;
import com.example.MutsaSNS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleImagesRepository articleImagesRepository;

    public void createArticle(ArticleDto articleDto) {
        // 작성자랑 타이틀이 있는지
        if (articleDto.getTitle() == null) {
            throw new TitleNullException();
        } else if (articleDto.getWriter() == null) {
            throw new WriterNullException();
        }
        // 작성자가 존재하는 유저인지
        if (!userRepository.existsByUsername(articleDto.getWriter())) {
            throw new UsernameNotFoundException();
        }
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleDto.getTitle());
        articleEntity.setWriter(userRepository.findByUsername(articleDto.getWriter()).get());
        articleEntity.setContent(articleDto.getContent());
        articleEntity.setCreatedAt(LocalDateTime.now());
        articleEntity.setDraft(false);
        articleRepository.save(articleEntity);
        // 기본 이미지 설정하기
        ArticleImagesEntity articleImagesEntity = new ArticleImagesEntity();
        articleImagesEntity.setArticle(articleEntity);
        articleImagesEntity.setImageUrl("media/articleImages/default.png");
        articleImagesEntity.setThumnail(true);
        articleImagesRepository.save(articleImagesEntity);
        articleEntity.setImages(new ArrayList<>());
        articleEntity.getImages().add(articleImagesEntity);
        articleRepository.save(articleEntity);
    }
}
