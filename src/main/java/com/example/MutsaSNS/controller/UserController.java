package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.CustomUserDetails;
import com.example.MutsaSNS.dtos.ResponseDto;
import com.example.MutsaSNS.dtos.UserFriendsDto;
import com.example.MutsaSNS.entities.UserEntity;
import com.example.MutsaSNS.exceptions.conflict.UsernameConflictException;
import com.example.MutsaSNS.exceptions.serverError.CustomUserDetailCastFailException;
import com.example.MutsaSNS.exceptions.unauthorized.PasswordNotMatchException;
import com.example.MutsaSNS.jwt.JwtRequestDto;
import com.example.MutsaSNS.jwt.JwtTokenUtils;
import com.example.MutsaSNS.service.JpaUserDetailsManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    // 회원 정보 읽기 API
    @GetMapping()
    public ResponseDto readUser(HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) manager.loadUserByUsername(username);
            responseDto.getResponse().put("user info", customUserDetails);
            responseDto.getResponse().put("message", "사용자 정보를 조회했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 프로필 이미지 등록 API
    @PutMapping(value = "/image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadProfileImg(
            HttpServletRequest request,
            @RequestParam("photo")MultipartFile multipartFile) throws IOException {
        ResponseDto responseDto = new ResponseDto();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        try {
            manager.uploadProfileImg(username, multipartFile);
            responseDto.getResponse().put("message", "프로필 사진을 업로드했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 팔로우 API
    @PutMapping("/follow/{followId}")
    public ResponseDto followUser(
            HttpServletRequest request,
            @PathVariable("followId") Long followId
    ) {
        ResponseDto responseDto = new ResponseDto();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        try {
            if (!manager.existFollow(username, followId)) {
                manager.createFollowRelationship(username, followId);
                responseDto.getResponse().put("message", "팔로우를 걸었습니다");
            }
            else {
                manager.deleteFollowRelationship(username, followId);
                responseDto.getResponse().put("message", "팔로우를 취소했습니다");
            }

        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 친구 API
    @PostMapping("/friend/{friendId}")
    public ResponseDto createFriendUser(
            HttpServletRequest request,
            @PathVariable("friendId") Long friendId
    ) {
        ResponseDto responseDto = new ResponseDto();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        try {
            manager.createFriendRelationship(username, friendId);
            responseDto.getResponse().put("message", "친구 신청을 걸었습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    @GetMapping("friend")
    public ResponseDto readFriendRequest (HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        try {
            List<UserFriendsDto> friendRequest = manager.reaedFriendRequest(username);
            if(friendRequest.size() == 0) responseDto.getResponse().put("result", "친구 요청이 없습니다");
            else responseDto.getResponse().put("result", friendRequest);
            responseDto.getResponse().put("message", "친구 요청 목록을 불러오는 데 성공했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

    // 친구 신청 거절 / 수락 API
    @PutMapping("friend")
    public ResponseDto decideFriendUser(
            HttpServletRequest request,
            @RequestBody UserFriendsDto dto
    ) {
        ResponseDto responseDto = new ResponseDto();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        String username = jwtTokenUtils
                .parseClaims(token)
                .getSubject();
        log.info(dto.toString());
        log.info(username);
        try {
            manager.decideFriendRequest(username, dto);
            if (dto.getStatus().equals("수락")) responseDto.getResponse().put("message", "친구 요청을 수락했습니다");
            if (dto.getStatus().equals("거절")) responseDto.getResponse().put("message", "친구 요청을 거절했습니다");
        } catch (RuntimeException error) {
            responseDto.getResponse().put("error", error.getMessage());
        }
        return responseDto;
    }

}
