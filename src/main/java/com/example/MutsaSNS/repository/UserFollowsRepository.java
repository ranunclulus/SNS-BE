package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.UserFollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowsRepository extends JpaRepository<UserFollowsEntity, Long> {
}
