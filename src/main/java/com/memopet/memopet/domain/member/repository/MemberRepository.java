package com.memopet.memopet.domain.member.repository;

import com.memopet.memopet.domain.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Member findByEmail(String email);
    Optional<Member> findByUsernameOrEmail(String username, String email);
    Optional<Member> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Member> findOneWithAuthoritiesByEmail(String email);

}
