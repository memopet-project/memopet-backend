package com.memopet.memopet.member;

import com.memopet.memopet.domain.member.entity.Authority;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberEntity() {
        // create user object
        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .authority(Authority.USER)
                .activated(true)
                .build();

        Member findMember = memberRepository.save(member);


        System.out.println(">>createdDate="+ findMember.getCreatedDate() + ", modifiedDate=" + findMember.getLastModifiedDate());
        System.out.println(">>Authority="+ findMember.getAuthority());
        System.out.println(">>Authority="+ findMember.getAuthorities());
        System.out.println(">>UUID="+ findMember.getId());

        // 비밀번호 일치 사용 법
        if (passwordEncoder.matches("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg3",findMember.getPassword())) {
            System.out.println("true");
        }
    }


}
