package com.memopet.memopet.domain.member.service;

import com.memopet.memopet.domain.member.dto.LoginDto;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService  {

    private final MemberRepository memberRepository;

    public void getLoginInfo(String JWTtoken, LoginDto loginDto) {
        Member member = memberRepository.findByEmail(loginDto.getEmail());

//        LoginResponseDto loginResponseDto = LoginResponseDto
//                                            .builder()
//                                            .login_fail_count(member.)
//                                            .build();
        return ;
    }
}
