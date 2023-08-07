package com.example.MutsaSNS.exceptions.badRequest;

public class SelfFollowNotAllowException extends Status400Exception{
    public SelfFollowNotAllowException() {

        super("자신을 팔로우할 수 없습니다");
    }
}
