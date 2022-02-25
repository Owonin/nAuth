package com.example.netflix.nAuth.controllers;

import com.example.netflix.nAuth.data.values.ErrorResponse;
import com.example.netflix.nAuth.data.values.TokenResponse;
import com.example.netflix.nAuth.data.values.User;
import com.example.netflix.nAuth.exceptions.RegistatoinException;
import com.example.netflix.nAuth.services.ClientService;
import com.example.netflix.nAuth.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) throws Exception {
        clientService.register(user.getClientId(), user.getClientSecret());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody User user) throws LoginException {
        clientService.checkCredentials(
                user.getClientId(), user.getClientSecret());
        return new TokenResponse(
                tokenService.generateToken(user.getClientId()));
    }

    @ExceptionHandler({RegistatoinException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
