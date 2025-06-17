package com.memopet.memopet.domain.member.repository;

import com.memopet.memopet.domain.member.dto.MemberInfoUpdateRequestDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.memopet.memopet.domain.member.entity.QMemberSocial.memberSocial;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory queryFactory;
    private final PasswordEncoder passwordEncoder;

    public CustomMemberRepositoryImpl(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void UpdateMemberInfo(MemberInfoUpdateRequestDto memberInfoUpdateRequestDto) {

        JPAUpdateClause clause  = queryFactory.update(memberSocial);

        if(memberUsernameEq(memberInfoUpdateRequestDto.getUsername()) != null) {
            clause.set(memberSocial.username, memberInfoUpdateRequestDto.getUsername());
        }
        if(memberPhoneNumEq(memberInfoUpdateRequestDto.getPhoneNum()) != null) {
            clause.set(memberSocial.phoneNum, memberInfoUpdateRequestDto.getPhoneNum());
        }
        clause.where(memberSocial.email.eq(memberInfoUpdateRequestDto.getEmail()));
        clause.execute();
    }

    private BooleanExpression memberPhoneNumEq(String phoneNum) {
        return phoneNum !=null? memberSocial.phoneNum.eq(phoneNum) : null;
    }

    private BooleanExpression memberUsernameEq(String username) {
        return username !=null? memberSocial.username.eq(username) : null;
    }

    private BooleanExpression memberPasswordEq(String password) {
        return password !=null? memberSocial.password.eq(password) : null;
    }
}
