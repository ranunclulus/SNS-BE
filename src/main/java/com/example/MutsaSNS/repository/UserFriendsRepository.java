package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.UserFollowsEntity;
import com.example.MutsaSNS.entities.UserFriendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFriendsRepository extends JpaRepository<UserFriendsEntity, Long> {

    boolean existsByFromUser_UsernameAndToUser_Id(String username, Long id);
    Optional<List<UserFriendsEntity>> findAllByToUser_Username(String username);
    Optional<UserFriendsEntity> findByFromUser_UsernameAndToUser_Username(String fromUser, String toUser);
    Optional<List<UserFriendsEntity>> findAllByFromUser_UsernameOrToUser_Username(String fromUser, String toUser);
}
