package com.memopet.memopet.member;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.global.common.entity.Comment;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest @Transactional @Rollback(value = false)
public class PetTest {
    @Autowired
    EntityManager em;
    @Test
    public void petAndMember() throws Exception {
        //given
        Member member = new Member("lazyslug@kakao.com");

        Pet pet = new Pet(member,
                "삶은 단순하고 아름다울 때,\n" +
                        "간단한 산책이 세상을 매료시킨다.\n" +
                        "눈부신 햇살, 싱그러운 바람,\n" +
                        "삶의 아름다움은 작은 순간에 담겨 있다."
                );
        em.persist(member);
        em.persist(pet);
        Assertions.assertThat(member).isEqualTo(pet.getMember());
        System.out.println("pet.getMember() = " + pet.getMember());
        //when
        Comment comment = new Comment(member,pet, "Meep");
        em.persist(comment);
        Memory memory = new Memory();
        em.persist(memory);
        Comment comment1 = new Comment(member,memory, "so sad");
        em.persist(comment1);
        //then
        System.out.println("pet = " + pet.getDescription());

    }
}
