package com.example.MutsaSNS.exceptions.badRequest;

public class WriterNotMatchException extends Status400Exception{
    public WriterNotMatchException() {

        super("작성자가 아닙니다");
    }
}
