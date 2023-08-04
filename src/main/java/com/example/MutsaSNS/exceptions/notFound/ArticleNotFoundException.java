package com.example.MutsaSNS.exceptions.notFound;

public class ArticleNotFoundException extends Status404Exception {
    public ArticleNotFoundException() {
        super("게시글을 찾을 수 없습니다");
    }
}
