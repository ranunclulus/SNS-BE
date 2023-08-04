package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.ArticleDto;
import com.example.MutsaSNS.entities.ArticleEntity;
import com.example.MutsaSNS.entities.ArticleImagesEntity;
import com.example.MutsaSNS.exceptions.badRequest.DeletedArticleException;
import com.example.MutsaSNS.exceptions.badRequest.TitleNullException;
import com.example.MutsaSNS.exceptions.badRequest.WriterNullException;
import com.example.MutsaSNS.exceptions.notFound.ArticleNotFoundException;
import com.example.MutsaSNS.exceptions.notFound.UsernameNotFoundException;
import com.example.MutsaSNS.repository.ArticleImagesRepository;
import com.example.MutsaSNS.repository.ArticleRepository;
import com.example.MutsaSNS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleImagesRepository articleImagesRepository;

    public void createArticle(Optional<List<MultipartFile>> images, ArticleDto articleDto) throws IOException {
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
        articleEntity.setImages(new ArrayList<>());
        articleEntity.setComments(new ArrayList<>());
        articleEntity.setLikes(new ArrayList<>());
        articleRepository.save(articleEntity);
        if(images.isPresent()) {
            for (int i = 0; i < images.get().size(); i++) {
                List<MultipartFile> imagesEntities = images.get();
                MultipartFile multipartFile = imagesEntities.get(i);
                Files.createDirectories(Path.of(String.format("media/articleImages/%s"), articleDto.getWriter()));
                LocalDateTime now = LocalDateTime.now();
                String imageUrl = String.format(
                        "media/articleImages/%s/%s.png", articleDto.getWriter(), now.toString());
                multipartFile.transferTo(Path.of(imageUrl));
                ArticleImagesEntity articleImagesEntity = new ArticleImagesEntity();
                if (i == 0) articleImagesEntity.setThumnail(true);
                else articleImagesEntity.setThumnail(false);
                articleImagesEntity.setImageUrl(imageUrl);
                articleImagesEntity.setArticle(articleEntity);
                articleEntity.getImages().add(articleImagesEntity);
            }
        }
        articleRepository.save(articleEntity);
    }

    public List<ArticleDto> readArticleByUser(String username) {
        // 작성자가 존재하는 유저인지
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException();
        }
        Optional<List<ArticleEntity>> optionalArticleEntities
                = articleRepository.findAllByWriter(userRepository.findByUsername(username).get());
        List<ArticleDto> articleDtos = new ArrayList<>();
        if (optionalArticleEntities.isPresent()) {
            for (ArticleEntity articleEntity:optionalArticleEntities.get()) {
                // 삭제되지 않았을 경우에만
                if(articleEntity.getDeletedAt() == null) {
                    ArticleDto articleDto = ArticleDto.fromEntity(articleEntity);
                    articleDtos.add(articleDto);
                }
            }
        }
        return articleDtos;
    }

    public ArticleDto readArticle(Long articleId) {
        Optional<ArticleEntity> optionalArticleEntity
                = articleRepository.findById(articleId);
        if(optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();
        ArticleEntity articleEntity = optionalArticleEntity.get();
        if(articleEntity.getDeletedAt() != null) throw new DeletedArticleException();
        return ArticleDto.fromEntity(articleEntity);
    }
}
