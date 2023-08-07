package com.example.MutsaSNS.exceptions.badRequest;

public class SelfLikeNotAllowException extends Status400Exception{
    public SelfLikeNotAllowException() {

        super("자신의 게시글에 좋아요를 남길 수 없습니다");
    }
}
