package com.example.MutsaSNS.exceptions.unauthorized;

public abstract class Status401Exception extends RuntimeException{
    public Status401Exception(String message) {
        super(message);
    }
}
