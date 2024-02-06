package com.memopet.memopet.domain.member.service;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.LoginFailedRepository;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.global.config.UserInfoConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final LoginFailedRepository loginFailedRepository;

    //private final JavaMailSender javaMailSender;
    private static final String USER_AUTHENTICATION_MESSAGE = "Enter the following verification code";

    public static final int MAX_ATTEMPT_COUNT = 5;

    @Override
    @Transactional(readOnly = false)
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername start with Email : " + Email);

        return memberRepository.findOneWithAuthoritiesByEmail(Email)
                .map(UserInfoConfig::new)
                .orElseThrow(() -> {throw new UsernameNotFoundException(Email + " -> 데이터베이스에서 찾을 수 없습니다.");});
    }

    private void countLoginFailed(Member member) {

        int attemptCount = loginFailedRepository.increment(member.getEmail());

        log.info("attemptCount : {}", attemptCount);

        if (attemptCount >= MAX_ATTEMPT_COUNT) {
            lockUserAccount(member);
            loginFailedRepository.resetCount(member.getUsername()); // 계정 잠금 후 실패 횟수 초기화
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void lockUserAccount(Member member) {

        /*
            계정 잠금 조치
                - 계정 activity 값을 locked 로 변경 (추가적인 인증 필요한 상태)
                - 계정 가입한 이메일로 보안 코드 발송
         */

        Member memberInfo = memberRepository.findByEmail(member.getEmail());

        loginFailedRepository.changeMemberStatusAndActivation(memberInfo, MemberStatus.LOCKED);
        //loginFailedRepository.toUnVerified(memberInfo);

//        javaMailSender.send(EmailForms.createEmailForm(user.getEmail(), USER_AUTHENTICATION_MESSAGE,
//                RandomCodeGenerator.createRandomCodeNumber()));
    }
}
