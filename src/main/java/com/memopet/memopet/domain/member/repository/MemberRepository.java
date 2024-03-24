package com.memopet.memopet.domain.member.repository;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.custom.MemberRepositoryCustom;
import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID>, MemberRepositoryCustom {
    Member findByEmail(String email);
    Optional<Member> findOneWithAuthoritiesByEmail(String email);

    @Query("select m from Member m where m.username= :username and m.phoneNum = :phoneNum")
    Member findIdByUsernameAndPhoneNum(@Param("username") String username, @Param("phoneNum") String phoneNum);

}
