package com.example.MutsaSNS.exceptions.serverError;

public class CustomUserDetailCastFailException extends Status500Exception{
    public CustomUserDetailCastFailException() {
        super("CustomUserDetail 형 변환을 실패했습니다");
    }
}
