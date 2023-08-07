package com.example.MutsaSNS.exceptions.badRequest;

public class ArticleAndImageNotMatchException extends Status400Exception{
    public ArticleAndImageNotMatchException() {

        super("해당 게시글의 이미지가 아닙니다");
    }
}
