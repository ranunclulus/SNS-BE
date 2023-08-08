package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.ArticleDto;
import com.example.MutsaSNS.dtos.LikeArticleDto;
import com.example.MutsaSNS.entities.*;
import com.example.MutsaSNS.exceptions.badRequest.*;
import com.example.MutsaSNS.exceptions.badRequest.ArticleAndImageNotMatchException;
import com.example.MutsaSNS.exceptions.notFound.ArticleImageNotFoundException;
import com.example.MutsaSNS.exceptions.notFound.ArticleNotFoundException;
import com.example.MutsaSNS.exceptions.notFound.FollowingNotFoundException;
import com.example.MutsaSNS.exceptions.notFound.UsernameNotFoundException;
import com.example.MutsaSNS.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleImagesRepository articleImagesRepository;
    private final LikeArticleRepository likeArticleRepository;
    private final UserFollowsRepository userFollowsRepository;

    public void createArticle(String username, ArticleDto articleDto) throws IOException {
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
        // 작성자 일치하는지
        if (!username.equals(articleDto.getWriter()))
            throw new WriterNotMatchException();
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
    }

    public List<ArticleDto> readArticleByUser(String username) {
        // 작성자가 존재하는 유저인지
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException();
        }
        Optional<List<ArticleEntity>> optionalArticleEntities
                = articleRepository.findAllByWriterOrderByCreatedAtDesc(userRepository.findByUsername(username).get());
        List<ArticleDto> articleDtos = new ArrayList<>();
        if (optionalArticleEntities.isPresent()) {
            for (ArticleEntity articleEntity:optionalArticleEntities.get()) {
                // 삭제되지 않았을 경우에만
                if(articleEntity.getDeletedAt() == null) {
                    ArticleDto articleDto = ArticleDto.fromEntity(articleEntity);
                    if (articleDto.getImageUrl().isEmpty()) articleDto.getImageUrl().add("/media/articleImages/default.png");
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
        ArticleDto articleDto = ArticleDto.fromEntity(articleEntity);
        if (articleDto.getImageUrl().isEmpty()) articleDto.getImageUrl().add("/media/articleImages/default.png");
        return articleDto;
    }

    public void deleteArticle(Long articleId) {
        Optional<ArticleEntity> optionalArticleEntity
                = articleRepository.findById(articleId);
        if(optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();
        ArticleEntity articleEntity = optionalArticleEntity.get();
        articleEntity.setDeletedAt(LocalDateTime.now());
        articleRepository.save(articleEntity);
    }

    public void uploadArticleImg(Long articleId, MultipartFile multipartFile) throws IOException {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        if (optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();
        ArticleEntity articleEntity = optionalArticleEntity.get();
        if(articleEntity.getDeletedAt() != null) throw new DeletedArticleException();
        log.info(articleEntity.getWriter().getUsername());
        // 파일 저장
        Files.createDirectories(Path.of(String.format("media/articleImages/%s", articleEntity.getWriter().getUsername())));
        LocalDateTime now = LocalDateTime.now();
        String imageUrl = String.format(
                "media/articleImages/%s/%s.png", articleEntity.getWriter().getUsername(), now.toString());
        multipartFile.transferTo(Path.of(imageUrl));
        // 이미지 저장
        ArticleImagesEntity articleImagesEntity = new ArticleImagesEntity();
        articleImagesEntity.setArticle(articleEntity);
        articleImagesEntity.setImageUrl(imageUrl);
        if (articleEntity.getImages().size() == 0) articleImagesEntity.setThumnail(true);
        else articleImagesEntity.setThumnail(false);
        articleImagesRepository.save(articleImagesEntity);
        // 게시글에도 저장
        articleEntity.getImages().add(articleImagesEntity);
        articleRepository.save(articleEntity);
    }

    public void deleteArticleImg(Long articleId, Long imageId) {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        if (optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();

        ArticleEntity articleEntity = optionalArticleEntity.get();
        if(articleEntity.getDeletedAt() != null) throw new DeletedArticleException();

        Optional<ArticleImagesEntity> optionalArticleImagesEntity
                = articleImagesRepository.findById(imageId);
        if (optionalArticleImagesEntity.isEmpty()) throw new ArticleImageNotFoundException();
        ArticleImagesEntity articleImagesEntity = optionalArticleImagesEntity.get();

        if (!articleImagesEntity.getArticle().equals(articleEntity))
            throw new ArticleAndImageNotMatchException();

        articleImagesRepository.delete(articleImagesEntity);
    }

    public boolean existLike(Long articleId, LikeArticleDto likeArticleDto) {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        if (optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();
        return likeArticleRepository.existsByArticle_IdAndWriter_Username(articleId, likeArticleDto.getWriter());
    }

    public void createArticleLike(Long articleId, LikeArticleDto likeArticleDto) {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        if (optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(likeArticleDto.getWriter());
        if (optionalUserEntity.isEmpty()) throw new UsernameNotFoundException();

        ArticleEntity articleEntity = optionalArticleEntity.get();
        UserEntity userEntity = optionalUserEntity.get();

        if(articleEntity.getDeletedAt() != null) throw new DeletedArticleException();

        if (articleEntity.getWriter().getUsername().equals(userEntity.getUsername()))
            throw new SelfLikeNotAllowException();

        if(articleEntity.getDeletedAt() != null)
            throw new DeletedArticleException();

        LikeArticleEntity likeArticleEntity = new LikeArticleEntity();
        likeArticleEntity.setArticle(articleEntity);
        likeArticleEntity.setWriter(userEntity);
        likeArticleRepository.save(likeArticleEntity);
    }

    public void deleteArticleLike(Long articleId, LikeArticleDto likeArticleDto) {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        if (optionalArticleEntity.isEmpty()) throw new ArticleNotFoundException();

        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(likeArticleDto.getWriter());
        if (optionalUserEntity.isEmpty()) throw new UsernameNotFoundException();

        ArticleEntity articleEntity = optionalArticleEntity.get();
        UserEntity userEntity = optionalUserEntity.get();

        if(articleEntity.getDeletedAt() != null) throw new DeletedArticleException();

        if (articleEntity.getWriter().getUsername().equals(userEntity.getUsername()))
            throw new SelfLikeNotAllowException();

        if(articleEntity.getDeletedAt() != null)
            throw new DeletedArticleException();

        Optional<LikeArticleEntity> optionalLikeArticleEntity
                = likeArticleRepository.findByArticle_IdAndWriter_Username(articleId, likeArticleDto.getWriter());

        likeArticleRepository.delete(optionalLikeArticleEntity.get());
    }


}
