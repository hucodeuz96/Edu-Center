package com.example.hucodeuz.controller;

import com.example.hucodeuz.dto.LoginDto;
import com.example.hucodeuz.security.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author "Husniddin Ulachov"
 * @created 3:34 PM on 7/16/2022
 * @project Edu-Center
 */
@Controller
@RequestMapping("/auth")

public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        Authentication authenticate =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        System.out.println(authenticate);

        if (authenticate.isAuthenticated()) {
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);
        }
        else {
            return ResponseEntity.status(409).body("Incorrect password");
        }
    }

}
