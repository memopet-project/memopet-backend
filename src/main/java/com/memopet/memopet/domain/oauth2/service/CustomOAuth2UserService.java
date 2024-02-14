package com.memopet.memopet.domain.oauth2.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;

import com.memopet.memopet.domain.oauth2.entity.OAuth2UserInfoFactory;

import com.memopet.memopet.global.token.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2User loadUser method start");
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(oAuth2UserRequest);

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional(readOnly = false)
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();

        Map<String,Object> attrs = oAuth2User.getAttributes();

        System.out.println(" saveOrUpdate start");
        Member member = saveOrUpdate(registrationId, attrs);
        System.out.println(" saveOrUpdate done");


//        Authentication authentication = authService.createAuthenticationObject(member);
//
        // Generate a JWT token



//        authService.saveUserRefreshToken(member,refreshToken);
//        ServletWebRequest servletContainer = (ServletWebRequest) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = servletContainer.getResponse();
//        System.out.println(response);
//        authService.creatRefreshTokenCookie(response,refreshToken);

        return new OAuth2UserPrincipal(member);
    }
    private Member saveOrUpdate(String registrationId, Map<String,Object> attrs){
        Map<String, String> personalInfoMap = (Map<String, String>) attrs.get("response");

        Member member = memberRepository.findByEmail(personalInfoMap.get("email"));

        if (member == null) {
            Member memberInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId,
                    personalInfoMap);

            member = memberRepository.save(memberInfo);
        }

        return member;
    }
}