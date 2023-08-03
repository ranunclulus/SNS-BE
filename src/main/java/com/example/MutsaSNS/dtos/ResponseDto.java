package com.example.MutsaSNS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
public class ResponseDto {
    private HashMap<Object, Object> response = new HashMap<>();
}
