package com.example.MutsaSNS.repository;

import com.example.MutsaSNS.entities.UserFriendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFriendsRepository extends JpaRepository<UserFriendsEntity, Long> {
}
