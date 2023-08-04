package com.example.MutsaSNS.exceptions.badRequest;

public class DeletedArticleException extends Status400Exception{
    public DeletedArticleException() {
        super("삭제된 게시글입니다");
    }
}
