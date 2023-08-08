package com.example.MutsaSNS.exceptions.badRequest;

public class SelfFriendNotAllowException extends Status400Exception{
    public SelfFriendNotAllowException() {

        super("자신에게 친구 신청을 걸 수 없습니다");
    }
}
