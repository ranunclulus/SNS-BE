package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.CustomUserDetails;
import com.example.MutsaSNS.entities.UserEntity;
import com.example.MutsaSNS.entities.UserFollowsEntity;
import com.example.MutsaSNS.entities.UserFriendsEntity;
import com.example.MutsaSNS.exceptions.badRequest.AlreadyExsistFrendshipException;
import com.example.MutsaSNS.exceptions.badRequest.DeletedUserException;
import com.example.MutsaSNS.exceptions.badRequest.SelfFollowNotAllowException;
import com.example.MutsaSNS.exceptions.badRequest.SelfFriendNotAllowException;
import com.example.MutsaSNS.exceptions.conflict.UsernameConflictException;
import com.example.MutsaSNS.exceptions.notFound.UsernameNotFoundException;
import com.example.MutsaSNS.exceptions.serverError.CustomUserDetailCastFailException;
import com.example.MutsaSNS.repository.UserFollowsRepository;
import com.example.MutsaSNS.repository.UserFriendsRepository;
import com.example.MutsaSNS.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final UserFollowsRepository userFollowsRepository;
    private final UserFriendsRepository userFriendsRepository;

    public JpaUserDetailsManager(UserRepository userRepository, UserFollowsRepository userFollowsRepository, UserFriendsRepository userFriendsRepository) {
        this.userRepository = userRepository;
        this.userFollowsRepository = userFollowsRepository;
        this.userFriendsRepository = userFriendsRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        if (this.userExists(user.getUsername())) {
            throw new UsernameConflictException();
        }
        try {
            this.userRepository.save(
                    ((CustomUserDetails) user).newEntity());
        } catch (ClassCastException classCastException) {
            log.error("failed to cast to {}", CustomUserDetails.class);
            throw new CustomUserDetailCastFailException();
        }
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity
                = userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            throw new UsernameNotFoundException();
        }
        return CustomUserDetails.fromEntity(optionalUserEntity.get());
    }

    public void uploadProfileImg(String username, MultipartFile multipartFile) throws IOException {
        Optional<UserEntity> optionalUserEntity
                = userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            throw new UsernameNotFoundException();
        }

        Files.createDirectories(Path.of("media/userProfiles"));
        LocalDateTime now = LocalDateTime.now();
        String imageUrl = String.format(
                "media/userProfiles/%s.png", now.toString());
        multipartFile.transferTo(Path.of(imageUrl));
        UserEntity targetUser = optionalUserEntity.get();
        targetUser.setProfileImg(imageUrl);
        userRepository.save(targetUser);
    }

    public boolean existFollow(String username, Long followId) {
        if (!userExists(username)) {
            throw new UsernameNotFoundException();
        }

        if (!userRepository.existsById(followId)) {
            throw new UsernameNotFoundException();
        }

        return userFollowsRepository.existsByFollower_UsernameAndFollowing_Id(username, followId);
    }

    public void createFollowRelationship(String username, Long followId) {
        UserFollowsEntity userFollowsEntity = new UserFollowsEntity();
        UserEntity follower = userRepository.findByUsername(username).get();
        UserEntity following = userRepository.findById(followId).get();
        if(follower.getDeletedAt() != null | following.getDeletedAt() != null)
            throw new DeletedUserException();
        if (follower.equals(following))
            throw new SelfFollowNotAllowException();
        userFollowsEntity.setFollower(follower);
        userFollowsEntity.setFollowing(following);
        userFollowsRepository.save(userFollowsEntity);
    }

    public void deleteFollowRelationship(String username, Long followId) {
        Optional<UserFollowsEntity> optionalUserFollowsEntity
                = userFollowsRepository.findByFollower_UsernameAndFollowing_Id(username, followId);
        userFollowsRepository.delete(optionalUserFollowsEntity.get());
    }

    public void createFriendRelationship(String username, Long friendId) {
        if (userFriendsRepository.existsByFromUser_UsernameAndToUser_Id(username, friendId))
            throw new AlreadyExsistFrendshipException();

        if(!this.userExists(username))
            throw new UsernameNotFoundException();

        if(!userRepository.existsById(friendId))
            throw new UsernameNotFoundException();

        Optional<UserEntity> optionalFromUser = userRepository.findByUsername(username);
        Optional<UserEntity> optionalToUser = userRepository.findById(friendId);

        if (optionalFromUser.get().getDeletedAt() != null &
        optionalToUser.get().getDeletedAt() != null)
            throw new DeletedUserException();

        if (optionalToUser.get().equals(optionalFromUser.get()))
            throw new SelfFriendNotAllowException();

        UserFriendsEntity userFriendsEntity = new UserFriendsEntity();

        if(optionalToUser.isPresent() & optionalFromUser.isPresent()) {
            userFriendsEntity.setFromUser(optionalFromUser.get());
            userFriendsEntity.setToUser(optionalToUser.get());
            userFriendsEntity.setStatus("요청");
            userFriendsRepository.save(userFriendsEntity);
        }
    }
}
