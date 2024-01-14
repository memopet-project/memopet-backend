package com.memopet.memopet.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.memopet.memopet.global.token.TokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.io.PrintWriter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {
         http   .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorizeRequests)->
                        authorizeRequests
                                //users 포함한 end point 보안 적용 X
                                .requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll() // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다.
                                .requestMatchers(new AntPathRequestMatcher("/error/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/favicon.ico")).permitAll()
                                .anyRequest().authenticated() // 그 외 인증 없이 접근X
                )
                .exceptionHandling(handle -> handle.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .exceptionHandling(handle -> handle.accessDeniedHandler(jwtAccessDeniedHandler))
                // enable h2-console
                .headers((headers)->
                        headers.contentTypeOptions(contentTypeOptionsConfig ->
                                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)))
                // disable session
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }

    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {

        private final HttpStatus status;
        private final String message;
    }

}
