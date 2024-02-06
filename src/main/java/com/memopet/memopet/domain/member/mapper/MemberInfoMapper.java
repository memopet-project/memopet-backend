package com.memopet.memopet.domain.member.mapper;

import com.memopet.memopet.domain.member.dto.SignUpDto;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberInfoMapper {
    private final PasswordEncoder passwordEncoder;
    public Member convertToEntity(SignUpDto signUpDto) {
        Member member = Member.builder()
                    .username(signUpDto.getUsername())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .email(signUpDto.getEmail())
                    .phoneNum(signUpDto.getPhoneNum())
                    .memberStatus(MemberStatus.NORMAL)
                    .loginFailCount(0)
                    .roles("ROLE_USER")
                    .activated(true)
                    .build();
        return member;
    }
}
