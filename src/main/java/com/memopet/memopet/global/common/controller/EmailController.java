package com.memopet.memopet.global.common.controller;

import com.memopet.memopet.global.common.dto.EmailAuthRequestDto;
import com.memopet.memopet.global.common.dto.EmailAuthResponseDto;
import com.memopet.memopet.global.common.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sign-in/verification")
    public EmailAuthResponseDto sendVerificationEmail(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        String auth = emailService.sendEmail(emailDto.getEmail());
        return EmailAuthResponseDto.builder().dscCode("1").authCode(auth).build();
    }

    @PostMapping("/sign-in/verification-email")
    public EmailAuthResponseDto checkVerificationCode(@RequestBody EmailAuthRequestDto emailDto) {

        EmailAuthResponseDto emailAuthResponseDto = emailService.checkVerificationCode(emailDto.getEmail(), emailDto.getCode());
        return emailAuthResponseDto;
    }

}
