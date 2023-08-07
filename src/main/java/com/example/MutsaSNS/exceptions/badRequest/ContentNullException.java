package com.example.MutsaSNS.exceptions.badRequest;

public class ContentNullException extends Status400Exception{
    public ContentNullException() {
        super("내용이 비어 있습니다");
    }
}
