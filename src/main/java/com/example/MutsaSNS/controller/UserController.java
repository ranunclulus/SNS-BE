package com.example.MutsaSNS.controller;

import com.example.MutsaSNS.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @PostMapping("/signup")
    public ResponseDto signUp() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("temp");
        return responseDto;
    }
}
