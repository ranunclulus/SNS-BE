package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserFollowsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity follower;

    @ManyToOne
    private UserEntity following;
}
