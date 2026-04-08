package com.ecommerce.dto;

public class AuthResponse {

    // JWT returned after a successful login.
    private String token;

    public AuthResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
