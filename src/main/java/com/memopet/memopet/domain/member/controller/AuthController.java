package com.memopet.memopet.domain.member.controller;

import com.amazonaws.Request;
import com.amazonaws.Response;
import com.memopet.memopet.domain.member.dto.*;
import com.memopet.memopet.domain.member.service.AuthService;
import com.memopet.memopet.domain.member.service.LoginService;
import com.memopet.memopet.global.common.dto.EmailAuthRequestDto;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class  AuthController {

    private final AuthService authService;
    private final LoginService loginService;

    /**
     * when a user tries to log-in, this method is triggered.
     * @param loginRequestDto
     * @param response
     * @return LoginResponseDto
     */
    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        System.out.println("sign-in start");

        boolean accountLock = loginService.isAccountLock(loginRequestDto.getEmail());

        if(accountLock) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                                                       .message("Account Is Locked")
                    .status("423").timestamp(LocalDateTime.now()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.LOCKED);
        }
        // get an authentication object to generate access and refresh token
        Authentication authentication = authService.authenicateUser(loginRequestDto);

        // generate access and refresh token
        LoginResponseDto loginResponseDto = authService.getJWTTokensAfterAuthentication(authentication,response);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/sign-in/password-reset")
    public PasswordResetResponseDto resetPassword(@RequestBody EmailAuthRequestDto emailAuthRequestDto) {
        PasswordResetResponseDto passwordResetResponseDto = loginService.checkValidEmail(emailAuthRequestDto.getEmail());

        if(passwordResetResponseDto.getDscCode().equals("1")) {
            passwordResetResponseDto = loginService.resetPassword(emailAuthRequestDto.getEmail());
        }

        return passwordResetResponseDto;
    }

    @GetMapping("/sign-in/duplication-check")
    public DuplicationCheckResponseDto emailDuplicationCheck(@RequestBody DuplicationCheckRequestDto duplicationCheckRequestDto ) {
        DuplicationCheckResponseDto duplicationCheckResponseDto = loginService.checkDupplication(duplicationCheckRequestDto.getEmail());
        return duplicationCheckResponseDto;
    }

    @GetMapping("/sign-in/my-id")
    public MyIdResponseDto findMyId(@RequestBody MyIdRequestDto myIdRequestDto ) {
        MyIdResponseDto myIdResponseDto = loginService.findIdByUsernameAndPhoneNum(myIdRequestDto.getUsername(), myIdRequestDto.getPhoneNum());

        return myIdResponseDto;
    }

    @PostMapping("/sign-in/my-password")
    public MyPasswordResponseDto changeMyPassword(@RequestBody MyPasswordRequestDto  myPasswordRequestDto) {
        MyPasswordResponseDto myPasswordResponseDto = loginService.saveNewPassword(myPasswordRequestDto.getEmail(), myPasswordRequestDto.getPassword());

        return myPasswordResponseDto;
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

    @GetMapping("/main")
    public String authenticateUser(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("main");



        return "TEST";
    }


}
