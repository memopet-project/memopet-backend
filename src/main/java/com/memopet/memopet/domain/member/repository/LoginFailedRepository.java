package com.memopet.memopet.domain.member.repository;


import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginFailedRepository {
    
    private final EntityManager em;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public void resetCount(String email) {
        Member member = memberRepository.findByEmail(email);
        member.increaseLoginFailCount(0);

    }
    @Transactional(readOnly = false)
    public int increment(String email) {
        Member member = memberRepository.findByEmail(email);
        member.increaseLoginFailCount(member.getLoginFailCount() + 1);

        return member.getLoginFailCount();
    }
    @Transactional(readOnly = false)
    public void changeMemberStatusAndActivation(Member memberInfo, MemberStatus memberStatus) {
        Member member = memberRepository.findByEmail(memberInfo.getEmail());
        member.changeActivity(false);
        member.changeMemberStatus(memberStatus);

    }

}
