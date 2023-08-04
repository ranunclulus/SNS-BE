package com.example.MutsaSNS.exceptions.notFound;

public class ArticleImageNotFoundException extends Status404Exception{
    public ArticleImageNotFoundException() {
        super("이미지를 찾을 수 없습니다");
    }
}
