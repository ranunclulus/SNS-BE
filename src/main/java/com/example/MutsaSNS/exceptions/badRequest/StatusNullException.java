package com.example.MutsaSNS.exceptions.badRequest;

public class StatusNullException extends Status400Exception{
    public StatusNullException() {
        super("상태가 비어 있습니다");
    }
}
