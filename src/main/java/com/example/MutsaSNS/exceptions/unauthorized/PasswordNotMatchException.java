package com.example.MutsaSNS.exceptions.unauthorized;

public class PasswordNotMatchException extends Status401Exception {
    public PasswordNotMatchException() {
        super("비밀번호가 일치하지 않습니다");
    }
}
