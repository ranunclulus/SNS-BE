package com.example.MutsaSNS.exceptions.badRequest;

public class WriterNullException extends Status400Exception{
    public WriterNullException() {
        super("작성자가 비어 있습니다");
    }
}
