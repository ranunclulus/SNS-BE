package com.example.MutsaSNS.exceptions.badRequest;

public class DeletedCommentException extends Status400Exception{
    public DeletedCommentException() {
        super("삭제된 댓글입니다");
    }
}
