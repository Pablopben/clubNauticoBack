package com.club.service;


import com.club.auth.AuthResponse;
import com.club.auth.LoginRequest;
import com.club.auth.RegisterRequest;
import com.club.config.SecurityConfig;
import com.club.entities.Rol;
import com.club.entities.User;
import com.club.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
//               request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.getToken(user);
        var refreshToken = jwtService.getRefreshToken(user);

        AuthResponse authResponse = new AuthResponse(jwt, refreshToken, user.getUsername());
        return authResponse;
    }

    public User register(RegisterRequest request) {
        User user = new User(request.getUserName(), request.getFirstName(), request.getLastName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), Rol.USUARIO);
        return userRepository.save(user);
    }
}
