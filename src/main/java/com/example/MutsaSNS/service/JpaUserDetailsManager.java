package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.CustomUserDetails;
import com.example.MutsaSNS.entities.UserEntity;
import com.example.MutsaSNS.exceptions.conflict.UsernameConflictException;
import com.example.MutsaSNS.exceptions.notFound.UsernameNotFoundException;
import com.example.MutsaSNS.exceptions.serverError.CustomUserDetailCastFailException;
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

    public JpaUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
