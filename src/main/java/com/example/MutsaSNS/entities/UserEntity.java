package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String profileImg;
    private String email;
    private String phone;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "writer")
    private List<ArticleEntity> articles;

    @OneToMany(mappedBy = "writer")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "writer")
    private List<LikeArticleEntity> likes;

}
