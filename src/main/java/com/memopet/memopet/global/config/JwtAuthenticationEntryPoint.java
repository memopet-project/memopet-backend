package com.memopet.memopet.global.config;

import com.memopet.memopet.domain.member.repository.LoginFailedRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    LoginFailedRepository loginFailedRepository;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        System.out.println(authException.getMessage());
        System.out.println(authException.getSuppressed());
        System.out.println(authException.toString());
        String errorMessage = null;
        int httpServletResponse = 0;
        if(authException instanceof BadCredentialsException || authException instanceof InternalAuthenticationServiceException){

            // check if the account is locked

            errorMessage = "Username과 Password가 맞지 않습니다. 다시 확인해 주십시오";
            httpServletResponse = HttpServletResponse.SC_BAD_REQUEST;
        }else if(authException instanceof DisabledException) {
            errorMessage = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
            httpServletResponse = HttpServletResponse.SC_BAD_REQUEST;
        }else{
            httpServletResponse = HttpServletResponse.SC_BAD_REQUEST;
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }


        response.sendError(httpServletResponse, errorMessage);
    }
}
