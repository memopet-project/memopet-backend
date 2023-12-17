package com.memopet.memopet.member;

import com.memopet.memopet.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    void findMember() {
        Member member = new Member("이재훈");

        em.persist(member);

        assertThat(member.getUsername()).isEqualTo("이재훈");
    }

}
