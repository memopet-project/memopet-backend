package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.QMember;
import com.memopet.memopet.domain.pet.dto.PetListResponseDTO;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.PetStatus;
import com.memopet.memopet.domain.pet.entity.QPet;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.memopet.memopet.domain.member.entity.QMember.*;
import static com.memopet.memopet.domain.pet.entity.QPet.pet;

@Repository
public class CustomPetRepositoryImpl implements CustomPetRepository{
    private final JPAQueryFactory queryFactory;

    public CustomPetRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<PetListResponseDTO> findPetsByEmail(Pageable pageable, String email) {

        List<PetListResponseDTO> content = queryFactory.select(
                        Projections.constructor(PetListResponseDTO.class,
                                pet.id,
                                pet.petName,
                                pet.petDesc))
                .from(QPet.pet)
                .innerJoin(pet.member, member)
                .where(member.email.eq(email))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new CaseBuilder()
                        .when(pet.petStatus.eq(PetStatus.ACTIVE)).then(0)
                        .otherwise(1)
                        .asc())
                .fetch();
        long total = queryFactory.select(pet.id)
                .from(pet)
                .innerJoin(QPet.pet.member, member)
                .where(member.email.eq(email))
                .fetchFirst();
        return new PageImpl<>(content, pageable, total);

    }

    @Override
    public List<Pet> findMyPets(Long petId) {
        QPet pet = QPet.pet;
        QMember member = QMember.member;

        // 펫 아이디로 해당 펫의 맴버와 연관된 모든 펫을 조회합니다.
        List<Pet> pets = queryFactory
                .selectFrom(pet)
                .innerJoin(pet.member, member)
                .where(pet.id.eq(petId))
                .fetch();

        return pets;

    }


}
