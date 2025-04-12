package com.memopet.memopet.domain.oauth2.controller;


import com.memopet.memopet.domain.member.dto.SocialLoginResponseDto;
import com.memopet.memopet.domain.oauth2.entity.SocialOauthToken;
import com.memopet.memopet.domain.oauth2.service.OauthService;
import com.memopet.memopet.global.common.dto.RestResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1")
public class SocialLoginController {
    private final OauthService oauthService;

    @PostMapping("/login/oauth2/code/{socialLoginType}")
    public RestResult socialLogin(@PathVariable(name = "socialLoginType") String socialLoginType, @RequestBody SocialOauthToken socialOauthToken, HttpServletRequest request){
        log.info("socialLogin start");
        SocialLoginResponseDto socialLoginResponseDto = oauthService.oAuthLogin(socialLoginType,socialOauthToken);

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("loginResponseDto", socialLoginResponseDto);
        return new RestResult(dataMap);
    }
}
