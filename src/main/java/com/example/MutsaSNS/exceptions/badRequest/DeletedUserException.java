package com.example.MutsaSNS.exceptions.badRequest;

public class DeletedUserException extends Status400Exception{
    public DeletedUserException() {
        super("탈퇴한 사용자입니다");
    }
}
