package com.example.MutsaSNS.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserFriendsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity fromUser;

    @ManyToOne
    private UserEntity toUser;
}
