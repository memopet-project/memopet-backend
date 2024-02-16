package com.memopet.memopet.domain.oauth2.handler;


import com.memopet.memopet.domain.oauth2.service.OAuth2UserPrincipal;

import com.memopet.memopet.global.token.JwtTokenGenerator;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    //private final JwtTokenGenerator jwtTokenGenerator;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2UserPrincipal member = (OAuth2UserPrincipal) authentication.getPrincipal();

        System.out.println("onAuthenticationSuccess start");
        System.out.println(member.getAttributes());

        //String accessToken = jwtTokenGenerator.generateAccessToken(authentication);
        //String refreshToken = jwtTokenGenerator.generateRefreshToken(authentication);

        //authService.creatRefreshTokenCookie(response,refreshToken);

        //authService.saveUserRefreshToken(member, refreshToken);
        //clearAuthenticationAttributes(request, response);
        //response.sendRedirect();

    }


}