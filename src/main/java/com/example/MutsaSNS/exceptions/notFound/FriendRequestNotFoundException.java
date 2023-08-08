package com.example.MutsaSNS.exceptions.notFound;

public class FriendRequestNotFoundException extends Status404Exception {
    public FriendRequestNotFoundException() {
        super("친구 요청 내용을 찾을 수 없습니다");
    }
}
