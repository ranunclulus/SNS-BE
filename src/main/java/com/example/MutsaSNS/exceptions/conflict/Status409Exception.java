package com.example.MutsaSNS.exceptions.conflict;

public abstract class Status409Exception extends RuntimeException{
    public Status409Exception(String message) {
        super(message);
    }
}
