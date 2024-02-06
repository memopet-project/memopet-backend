package com.memopet.memopet.global.config;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class InitialUserInfo implements CommandLineRunner {
    // member test data insert
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    @Override
    public void run(String... args) throws Exception {
        Member member1 = Member.builder()
                .username("Test1")
                .password(passwordEncoder.encode("123"))
                .email("user@gmail.com")
                .loginFailCount(0)
                .phoneNum("01054232322")
                .memberStatus(MemberStatus.NORMAL)
                .roles("ROLE_USER")
                .activated(true)
                .build();
        Member member2 = Member.builder()
                .username("Test2")
                .password(passwordEncoder.encode("123"))
                .email("admin@gmail.com")
                .loginFailCount(0)
                .phoneNum("01084232322")
                .memberStatus(MemberStatus.NORMAL)
                .roles("ROLE_ADMIN")
                .activated(true)
                .build();

        memberRepository.saveAll(List.of(member1,member2));
    }
}
