package com.memopet.memopet.domain.member.controller;

import com.memopet.memopet.domain.member.dto.LoginRequestDto;
import com.memopet.memopet.domain.member.dto.LoginResponseDto;
import com.memopet.memopet.domain.member.dto.SignUpRequestDto;
import com.memopet.memopet.domain.member.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * when a user tries to log-in, this method is triggered.
     * @param loginRequestDto
     * @param response
     * @return LoginResponseDto
     */
    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {

        // get an authentication object to generate access and refresh token
        Authentication authentication = authService.authenicateUser(loginRequestDto);

        // generate access and refresh token
        LoginResponseDto loginResponseDto = authService.getJWTTokensAfterAuthentication(authentication,response);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    /**
     * when a user tries to sign-up
     * @param signUpRequestDto
     * @param bindingResult
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequestDto, BindingResult bindingResult, HttpServletResponse httpServletResponse){
        System.out.println("registerUser start");

        log.info("[AuthController:registerUser]Signup Process Started for user:{}", signUpRequestDto.getUsername());
        if (bindingResult.hasErrors()) {
            List<String> errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            log.error("[AuthController:registerUser]Errors in user:{}",errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        return ResponseEntity.ok(authService.join(signUpRequestDto, httpServletResponse));
    }

    @PreAuthorize("hasAuthority('SCOPE_REFRESH_TOKEN')")
    @PostMapping ("/refresh-token")
    public ResponseEntity<?> getAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return ResponseEntity.ok(authService.getAccessTokenUsingRefreshToken(authorizationHeader));
    }
}
