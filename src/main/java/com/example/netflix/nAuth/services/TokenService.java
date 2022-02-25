package com.example.netflix.nAuth.services;

public interface TokenService {
    String generateToken(String clientId);
}
