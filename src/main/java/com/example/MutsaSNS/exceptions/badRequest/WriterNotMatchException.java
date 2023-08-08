package com.example.MutsaSNS.exceptions.badRequest;

public class WriterNotMatchException extends Status400Exception{
    public WriterNotMatchException() {

        super("작성자가 일치하지 않습니다");
    }
}
