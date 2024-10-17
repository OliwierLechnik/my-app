package com.example.myapp;

import java.util.Base64;

public final class MyBase64{

    private MyBase64(){}

    public static String encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

}