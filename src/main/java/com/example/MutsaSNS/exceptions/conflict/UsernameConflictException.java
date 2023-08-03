package com.example.MutsaSNS.exceptions.conflict;

public class UsernameConflictException extends Status409Exception{
    public UsernameConflictException() {
        super("이미 존재하는 아이디입니다");
    }
}
