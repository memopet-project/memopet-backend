package com.memopet.memopet.domain.member.repository;


import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LoginFailedRepository {
    
    private final EntityManager em;
    private final MemberRepository memberRepository;


    public void resetCount(Member member) {
        member.increaseLoginFailCount(0);

    }

    public int increment(Member member) {
        member.increaseLoginFailCount(member.getLoginFailCount() + 1);
        return member.getLoginFailCount();
    }
    public void changeMemberStatusAndActivation(Member member, MemberStatus memberStatus) {
        member.changeActivity(false);
        member.changeMemberStatus(memberStatus);
    }

}
