package com.example.MutsaSNS.exceptions.notFound;

public class FollowingNotFoundException extends Status404Exception{
    public FollowingNotFoundException() {
        super("팔로우 중인 사람이 없습니다");
    }
}
