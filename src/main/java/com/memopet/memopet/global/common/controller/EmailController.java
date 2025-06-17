package com.memopet.memopet.global.common.controller;

import com.memopet.memopet.global.common.dto.EmailAuthRequestDto;
import com.memopet.memopet.global.common.dto.EmailAuthResponseDto;
import com.memopet.memopet.global.common.dto.RestResult;
import com.memopet.memopet.global.common.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sign-in/verification")
    public RestResult sendVerificationEmail(@RequestBody EmailAuthRequestDto emailDto) {
        EmailAuthResponseDto emailAuthResponseDto = emailService.sendEmail(emailDto.getEmail());
        return new RestResult(Map.of("response", emailAuthResponseDto));
    }

    @PostMapping("/sign-in/verification-email")
    public RestResult checkVerificationCode(@RequestBody EmailAuthRequestDto emailAuthRequestDto) {
        EmailAuthResponseDto emailAuthResponseDto = emailService.checkVerificationCode(emailAuthRequestDto);

        return new RestResult(Map.of("emailAuthResponseDto", emailAuthResponseDto));
    }

}
