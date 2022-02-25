package com.example.netflix.nAuth.services;

import com.example.netflix.nAuth.exceptions.RegistatoinException;

import javax.security.auth.login.LoginException;

public interface ClientService {
    void register(String clientId, String clientSecret) throws Exception, RegistatoinException;
    void checkCredentials(String clientId, String clientSecret) throws LoginException;
}
