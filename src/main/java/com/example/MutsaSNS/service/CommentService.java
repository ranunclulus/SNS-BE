package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.CommentDto;
import com.example.MutsaSNS.entities.ArticleEntity;
import com.example.MutsaSNS.entities.CommentEntity;
import com.example.MutsaSNS.entities.UserEntity;
import com.example.MutsaSNS.exceptions.badRequest.ContentNullException;
import com.example.MutsaSNS.exceptions.badRequest.WriterNullException;
import com.example.MutsaSNS.exceptions.notFound.ArticleNotFoundException;
import com.example.MutsaSNS.exceptions.notFound.UsernameNotFoundException;
import com.example.MutsaSNS.repository.ArticleRepository;
import com.example.MutsaSNS.repository.CommentRepository;
import com.example.MutsaSNS.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public void createComment(Long articleId, CommentDto commentDto) {
        if(commentDto.getWriter() == null) throw new WriterNullException();
        if(commentDto.getContent() == null) throw new ContentNullException();
        CommentEntity commentEntity = new CommentEntity();

        Optional<ArticleEntity> articleEntity = articleRepository.findById(articleId);
        if (articleEntity.isEmpty()) throw new ArticleNotFoundException();
        else commentEntity.setArticle(articleEntity.get());

        commentEntity.setContent(commentDto.getContent());

        Optional<UserEntity> userEntity = userRepository.findByUsername(commentDto.getWriter());
        if (userEntity.isEmpty()) throw new UsernameNotFoundException();
        else commentEntity.setWriter(userEntity.get());

        commentRepository.save(commentEntity);
    }
}
