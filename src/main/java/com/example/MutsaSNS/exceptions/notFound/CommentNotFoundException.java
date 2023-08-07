package com.example.MutsaSNS.exceptions.notFound;

public class CommentNotFoundException extends Status404Exception {
    public CommentNotFoundException() {
        super("댓글을 찾을 수 없습니다");
    }
}
