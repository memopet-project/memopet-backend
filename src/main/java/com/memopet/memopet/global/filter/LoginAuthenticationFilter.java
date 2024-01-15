package com.memopet.memopet.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memopet.memopet.domain.member.dto.LoginDto;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

/**
 * use this class in Security config file.
 *
 */
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public LoginAuthenticationFilter(final String defaultFilterProcessesUrl,
                                     final AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }


    // need to overider this in order to use Restful apis instead of form_based interaction.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException {

        System.out.println("attemptAuthentication start");
        String method = request.getMethod();

        if (!method.equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        ServletInputStream inputStream = request.getInputStream();

        LoginDto loginRequestDto = new ObjectMapper().readValue(inputStream, LoginDto.class);

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        ));
    }

}
