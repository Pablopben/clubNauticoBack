package com.club.controller;

import com.club.auth.AuthResponse;
import com.club.auth.LoginRequest;
import com.club.auth.RegisterRequest;
import com.club.entities.User;
import com.club.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

}
