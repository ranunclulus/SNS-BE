package com.example.MutsaSNS.service;

import com.example.MutsaSNS.dtos.CustomUserDetails;
import com.example.MutsaSNS.exceptions.conflict.UsernameConflictException;
import com.example.MutsaSNS.exceptions.serverError.CustomUserDetailCastFailException;
import com.example.MutsaSNS.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return null;
    }
}
