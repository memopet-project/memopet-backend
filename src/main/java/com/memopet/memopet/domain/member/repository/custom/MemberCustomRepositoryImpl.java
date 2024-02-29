package com.memopet.memopet.domain.member.repository.custom;

import com.memopet.memopet.domain.member.dto.MemberInfoRequestDto;
import com.memopet.memopet.domain.member.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

public class MemberCustomRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;



    public MemberCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


}
