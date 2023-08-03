package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.CustomUserDetails;
import com.example.MutsaSNS.dtos.ResponseDto;
import com.example.MutsaSNS.exceptions.conflict.UsernameConflictException;
import com.example.MutsaSNS.exceptions.serverError.CustomUserDetailCastFailException;
import com.example.MutsaSNS.exceptions.unauthorized.PasswordNotMatchException;
import com.example.MutsaSNS.jwt.JwtRequestDto;
import com.example.MutsaSNS.jwt.JwtTokenUtils;
import com.example.MutsaSNS.service.JpaUserDetailsManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final JpaUserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    // 회원 가입 API
    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody CustomUserDetails customUserDetails) {
        ResponseDto responseDto = new ResponseDto();
        try {
            manager.createUser(CustomUserDetails.builder()
                    .username(customUserDetails.getUsername())
                    .password(passwordEncoder.encode(customUserDetails.getPassword()))
                    .email(customUserDetails.getEmail())
                    .phone(customUserDetails.getPhone())
                    .build());
            responseDto.getResponse().put("message", "회원 가입을 완료했습니다");
        } catch (UsernameConflictException | CustomUserDetailCastFailException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }


    // 로그인 API
    @PostMapping("/signin")
    public ResponseDto signin(@RequestBody JwtRequestDto jwtRequestDto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            CustomUserDetails customUserDetails
                    = (CustomUserDetails) manager.loadUserByUsername(jwtRequestDto.getUsername());

            if (!passwordEncoder.matches(jwtRequestDto.getPassword(), customUserDetails.getPassword())) {
                throw new PasswordNotMatchException();
            }
            responseDto.getResponse().put("token", jwtTokenUtils.generateToken(jwtRequestDto.getUsername()));
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }

        return responseDto;
    }
}
