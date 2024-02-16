package com.memopet.memopet.domain.oauth2.entity;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.oauth2.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static Member getOAuth2UserInfo(String registrationId, Map<String,String> personalInfoMap) {

        if (OAuth2Provider.GOOGLE.getRegistrationId().equals(registrationId) || OAuth2Provider.NAVER.getRegistrationId().equals(registrationId)) {
            return Member.builder()
                         .username(personalInfoMap.get("name"))
                         .email(personalInfoMap.get("email"))
                         .roles("ROLE_USER")
                         .loginFailCount(0)
                         .memberStatus(MemberStatus.NORMAL)
                         .provideId(personalInfoMap.get("id"))
                         .provider(registrationId)
                         .activated(true)
                         .build();



        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported");
        }
    }
}
