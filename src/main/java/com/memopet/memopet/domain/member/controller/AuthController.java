package com.memopet.memopet.domain.member.controller;

import com.memopet.memopet.domain.member.dto.LoginDto;
import com.memopet.memopet.domain.member.dto.SignUpDto;
import com.memopet.memopet.domain.member.dto.TokenDto;
import com.memopet.memopet.domain.member.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("authenticateUser start");
        // process the authentication logic for the user login.
        Authentication authentication = authService.authenicateUser(loginDto);

        // create JWT token
        String JWTtoken = authService.createToken(authentication);

        // store jwt token in response header
        // HttpHeaders httpHeaders = authService.createHttpHeaders(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + JWTtoken);

        // return response body with TokenDto
        return new ResponseEntity<>(new TokenDto(JWTtoken), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto){
        String response = authService.join(signUpDto);
        // . d
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}