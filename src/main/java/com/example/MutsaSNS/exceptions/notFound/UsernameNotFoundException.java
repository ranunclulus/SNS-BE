package com.example.MutsaSNS.exceptions.notFound;

public class UsernameNotFoundException extends Status404Exception{
    public UsernameNotFoundException() {
        super("아이디에 해당하는 사용자를 찾을 수 없습니다");
    }
}
