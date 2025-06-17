package com.memopet.memopet.domain.member.controller;

import com.memopet.memopet.domain.member.dto.*;
import com.memopet.memopet.domain.member.service.AuthService;
import com.memopet.memopet.domain.member.service.LoginService;
import com.memopet.memopet.global.common.dto.RestResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "인증", description = "인증 관련 api 입니다.")	// (1)
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
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
    public RestResult authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // get an authentication object to generate access and refresh token
        Authentication authentication = authService.authenticateUser(loginRequestDto);
        // generate access and refresh token
        LoginResponseDto loginResponseDto = authService.getJWTTokensAfterAuthentication(authentication);

        response.setHeader("Authorization", "Bearer " + loginResponseDto.getAccessToken());
        return new RestResult(Map.of("loginInfo", loginResponseDto));

    }

    @GetMapping("/sign-in/duplication-check")
    public RestResult emailDuplicationCheck(DuplicationCheckRequestDto duplicationCheckRequestDto ) {
        DuplicationCheckResponseDto duplicationCheckResponseDto = loginService.checkDuplication(duplicationCheckRequestDto.getEmail());

        return new RestResult(Map.of("duplicationCheckResponse", duplicationCheckResponseDto));
    }

    @PostMapping("/sign-in/my-id")
    public RestResult findMyId(@RequestBody MyIdRequestDto myIdRequestDto) {
        MyIdResponseDto myIdResponseDto = loginService.findIdByUsernameAndPhoneNum(myIdRequestDto.getUsername(), myIdRequestDto.getPhoneNum());

        return new RestResult(Map.of("findMyIdResponse", myIdResponseDto));
    }

    @PostMapping("/sign-in/my-password")
    public RestResult changeMyPassword(@RequestBody MyPasswordRequestDto  myPasswordRequestDto) {
        MyPasswordResponseDto myPasswordResponseDto = loginService.saveNewPassword(myPasswordRequestDto.getEmail(), myPasswordRequestDto.getPassword());

        return new RestResult(Map.of("changeMyPasswordResponse", myPasswordResponseDto));
    }

    @PostMapping("/sign-in/password-reset")
    public RestResult resetMyPassword(@RequestBody MyPasswordRequestDto  myPasswordRequestDto) {
        ResetPasswordResponseDto resetPasswordResponseDto = loginService.resetNewPassword(myPasswordRequestDto.getEmail());

        return new RestResult(Map.of("changeMyPasswordResponse", resetPasswordResponseDto));
    }

    /**
     * when a user tries to sign-up
     * @param signUpRequestDto
     * @return
     */
    @PostMapping("/sign-up")
    public RestResult registerUser(@Valid @RequestBody SignUpRequestDto signUpRequestDto, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.join(signUpRequestDto);

        response.setHeader("Authorization", "Bearer " + loginResponseDto.getAccessToken());

        return new RestResult(Map.of("sigupInfo", loginResponseDto));
    }


}
