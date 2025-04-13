package com.memopet.memopet.domain.member.service;

import com.memopet.memopet.domain.member.dto.DuplicationCheckResponseDto;
import com.memopet.memopet.domain.member.dto.MyIdResponseDto;
import com.memopet.memopet.domain.member.dto.MyPasswordResponseDto;
import com.memopet.memopet.domain.member.dto.ResetPasswordResponseDto;
import com.memopet.memopet.domain.member.entity.MemberSocial;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.LoginFailedRepository;
import com.memopet.memopet.domain.member.repository.MemberSocialRepository;
import com.memopet.memopet.global.common.dto.EmailAuthResponseDto;
import com.memopet.memopet.global.common.entity.Audit;
import com.memopet.memopet.global.common.exception.BadLoginCredentialsException;
import com.memopet.memopet.global.common.repository.AuditRepository;
import com.memopet.memopet.global.common.service.EmailService;
import com.memopet.memopet.global.common.utils.BusinessUtil;
import com.memopet.memopet.global.config.UserInfoConfig;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LoginService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberSocialRepository memberSocialRepository;
    private final LoginFailedRepository loginFailedRepository;
    private final AuditRepository auditRepository;
    private final EntityManager entityManager;
    private final EmailService emailService;
    public static final int MAX_ATTEMPT_COUNT = 5;
    private final BusinessUtil businessUtil;

    @Override
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberSocialRepository.findMemberByEmail(email)
                .map(UserInfoConfig::new)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = BadLoginCredentialsException.class)
    public int loginAttemptCheck(MemberSocial memberSocial,String password) {
        // memberSocial을 현재 트랜잭션에 병합
        MemberSocial managedMemberSocial = entityManager.merge(memberSocial);

        boolean isCorrectPassword = passwordEncoder.matches(password, managedMemberSocial.getPassword());
        // check if the input password is correct
        if (isCorrectPassword) {
            loginFailedRepository.resetCount(managedMemberSocial); // reset the count of login failure attempts
            return 1;
        }
        int failCount = managedMemberSocial.getLoginFailCount() + 1;

        if (failCount >= MAX_ATTEMPT_COUNT) {
            changeAccountStatus(MemberStatus.LOCKED, managedMemberSocial);

            Audit audit = Audit.builder()
                    .createdDate(LocalDateTime.now())
                    .cnbf("account is active")
                    .cnaf("account is locked")
                    .modifier(managedMemberSocial.getEmail())
                    .build();

            // save audit log
            auditRepository.save(audit);


        } else {
            log.info("login attempt failed {}", failCount);
            managedMemberSocial.increaseLoginFailCount(failCount);
        }
        entityManager.flush();
        throw new BadLoginCredentialsException(String.valueOf(failCount));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeAccountStatus(MemberStatus memberStatus, MemberSocial memberSocial) {
        loginFailedRepository.changeMemberStatusAndActivation(memberSocial, memberStatus);
        loginFailedRepository.resetCount(memberSocial); // 계정 잠금 후 실패 횟수 초기화
    }

    public DuplicationCheckResponseDto checkDuplication(String email) {

        return memberSocialRepository.findMemberByEmail(email)
                .map(memberSocial -> DuplicationCheckResponseDto.builder().dscCode("1").errMessage("Email is valid").build())
                .orElseGet(() -> DuplicationCheckResponseDto.builder().dscCode("1").errMessage("Email is valid").build());
    }

    public MyIdResponseDto findIdByUsernameAndPhoneNum(String username, String phoneNum) {
        return memberSocialRepository.findIdByUsernameAndPhoneNum(username,phoneNum)
                .map(memberSocial -> {
                    String dscCode = (memberSocial.getProviderId() == null) ? "1" : "2";

                    return MyIdResponseDto.builder()
                            .dscCode(dscCode)
                            .email(memberSocial.getEmail())
                            .socialLoginProvider( memberSocial.getProvider())
                            .build();
                })
                .orElseGet(() -> MyIdResponseDto.builder().dscCode("0").build());
    }

    public MyPasswordResponseDto saveNewPassword(String email, String password) {
        MemberSocial memberSocial = businessUtil.getValidEmail(email);
        memberSocial.changePassword(passwordEncoder.encode(password));

        return MyPasswordResponseDto.builder().dscCode("1").build();
    }

    @Transactional(readOnly = false)
    public ResetPasswordResponseDto resetNewPassword(String email) {
        MemberSocial memberSocial = businessUtil.getValidEmail(email);

        EmailAuthResponseDto emailAuthResponseDto = emailService.sendEmail(memberSocial.getEmail());

        memberSocial.changePassword(passwordEncoder.encode(emailAuthResponseDto.getAuthCode()));
        memberSocial.changeMemberStatus(MemberStatus.NORMAL);
        memberSocial.resetLoginFailureAttempts();

        return  ResetPasswordResponseDto.builder().dscCode("1").newPassword(emailAuthResponseDto.getAuthCode()).build();
    }
}
