package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.UserEntity;
import com.example.MutsaSNS.entities.UserFollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFollowsRepository extends JpaRepository<UserFollowsEntity, Long> {
    boolean existsByFollower_UsernameAndFollowing_Id(String username, Long id);
    Optional<UserFollowsEntity> findByFollower_UsernameAndFollowing_Id(String username, Long id);

    Optional<List<UserFollowsEntity>> findAllByFollower_Username(String username);
}
