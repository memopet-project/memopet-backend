package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.FollowListResponseDto;

import com.memopet.memopet.domain.pet.entity.Follow;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.QFollow;
import com.memopet.memopet.domain.pet.entity.QPet;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static com.memopet.memopet.domain.pet.entity.QFollow.follow;
import static com.memopet.memopet.domain.pet.entity.QPet.*;

@Repository
public class CustomFollowRepositoryImpl implements CustomFollowRepository{

    private final JPAQueryFactory queryFactory;

    public CustomFollowRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
    @Override
    public Page<FollowListResponseDto> findFollowingPetsById(Pageable pageable,Long petId) {
        List<FollowListResponseDto> content = queryFactory.select(
                        Projections.constructor(FollowListResponseDto.class,
                                follow.followingPet.id,
                                follow.followingPet.petName,
                                follow.followingPet.petDesc,
                                follow.followingPet.petProfileUrl
                        ))
                .from(QFollow.follow)
                .where(QFollow.follow.petId.eq(petId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> countQuery = queryFactory
                .select(follow.count())
                .from(follow)
                .where(follow.petId.eq(petId));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

}

    @Override
    public Page<FollowListResponseDto> findFollowerPetsByPetId(Pageable pageable, Long petId) {
        List<FollowListResponseDto> content = queryFactory.select(
                        Projections.constructor(FollowListResponseDto.class,
                                pet.id,
                                pet.petName,
                                pet.petDesc,
                                pet.petProfileUrl
                        ))
                .from(QFollow.follow)
                .leftJoin(pet).on(follow.petId.eq(pet.id))
                .where(follow.followingPet.id.eq(petId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(follow.count())
                .from(follow)
                .where(follow.petId.eq(petId));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

}
