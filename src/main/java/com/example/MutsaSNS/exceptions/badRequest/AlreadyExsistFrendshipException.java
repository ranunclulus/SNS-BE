package com.example.MutsaSNS.exceptions.badRequest;

public class AlreadyExsistFrendshipException extends Status400Exception{
    public AlreadyExsistFrendshipException() {

        super("이미 친구 신청을 걸었습니다");
    }
}
