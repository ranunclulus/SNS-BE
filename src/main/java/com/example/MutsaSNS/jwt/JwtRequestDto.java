package com.example.MutsaSNS.jwt;
import lombok.Data;

@Data
public class JwtRequestDto {
    private String username;
    private String password;
}