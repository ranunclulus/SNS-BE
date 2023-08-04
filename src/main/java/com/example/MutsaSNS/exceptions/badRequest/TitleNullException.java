package com.example.MutsaSNS.exceptions.badRequest;

public class TitleNullException extends Status400Exception{
    public TitleNullException() {
        super("제목이 비어 있습니다");
    }
}
