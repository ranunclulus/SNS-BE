package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.CustomUserDetails;
import com.example.MutsaSNS.dtos.ResponseDto;
import com.example.MutsaSNS.exceptions.conflict.UsernameConflictException;
import com.example.MutsaSNS.exceptions.serverError.CustomUserDetailCastFailException;
import com.example.MutsaSNS.service.JpaUserDetailsManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final JpaUserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody CustomUserDetails customUserDetails) {
        ResponseDto responseDto = new ResponseDto();
        log.info(customUserDetails.getPassword());
        try {
            manager.createUser(CustomUserDetails.builder()
                    .username(customUserDetails.getUsername())
                    .password(passwordEncoder.encode(customUserDetails.getPassword()))
                    .email(customUserDetails.getEmail())
                    .phone(customUserDetails.getPhone())
                    .build());
            responseDto.setMessage("회원 가입을 완료했습니다");
        } catch (UsernameConflictException | CustomUserDetailCastFailException error) {
            responseDto.setMessage(error.getMessage());
        }
        return responseDto;
    }
}
