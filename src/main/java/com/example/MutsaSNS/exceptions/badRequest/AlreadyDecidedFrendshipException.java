package com.example.MutsaSNS.exceptions.badRequest;

public class AlreadyDecidedFrendshipException extends Status400Exception{
    public AlreadyDecidedFrendshipException() {

        super("이미 처리된 친구 요청입니다");
    }
}
