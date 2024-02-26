package com.memopet.memopet.pet;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.RefreshTokenEntity;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.dto.FollowDTO;
import com.memopet.memopet.domain.pet.entity.Gender;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.PetStatus;
import com.memopet.memopet.domain.pet.entity.Species;
import com.memopet.memopet.domain.pet.repository.FollowRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.domain.pet.service.FollowService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class FollowTest {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    FollowService followService;

    @Autowired
    FollowRepository followRepository;


    @Test
    public void FollowAPet() throws Exception {
        //given
        FollowDTO followDTO = new FollowDTO(1L, 2L);
        int result= followService.followAPet(followDTO).getResultCode();
        System.out.println("result = " + result);
        FollowDTO followDTO1 = new FollowDTO(1L, 3L);
        int result2= followService.followAPet(followDTO1).getResultCode();
        System.out.println("result2 = " + result2);
    }

    @Test
    public void followersAndFollowings() throws Exception {

        FollowDTO followDTO4 = new FollowDTO(2L, 3L);
        followService.followAPet(followDTO4);

        FollowDTO followDTO2 = new FollowDTO(1L, 3L);
        followService.followAPet(followDTO2);

        boolean result=followRepository.existsByPetIdAndFollowingPetId(2L, 3L);
        System.out.println("result = " + result);


    }
    @Test
    public void unfollow() throws Exception {
        //given
        FollowDTO followDTO4 = new FollowDTO(2L, 3L);
        followService.followAPet(followDTO4);

        FollowDTO followDTO2 = new FollowDTO(1L, 3L);
        followService.followAPet(followDTO2);

//        when
        boolean result = followRepository.existsByPetIdAndFollowingPetId(1L, 3L); //질저장되었는지 확인
        Assertions.assertThat(result).isTrue();

        //then
        followRepository.deleteByPetIdAndFollowingPetId(followDTO2.getPetId(),followDTO2.getFollowingPetId()); //언팔로우
        boolean result1 = followRepository.existsByPetIdAndFollowingPetId(1L, 3L); //디비에 없는지 확인
        Assertions.assertThat(result1).isFalse();
    }
    @Test
    public void unfollowFromService() throws Exception {
        //given
        FollowDTO followDTO4 = new FollowDTO(2L, 3L);
        followService.followAPet(followDTO4);

        FollowDTO followDTO2 = new FollowDTO(1L, 3L);
        followService.followAPet(followDTO2);

        //then
        followService.unfollow(followDTO2);
        boolean result1 = followRepository.existsByPetIdAndFollowingPetId(1L, 3L); //디비에 없는지 확인
        Assertions.assertThat(result1).isFalse();
    }


    @PostConstruct
    void init() {



        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae4@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .roles("ROLE_USER")
                .activated(true)
                .build();
        Member findmember = memberRepository.save(member);

        RefreshTokenEntity myToken= (RefreshTokenEntity) findmember.getRefreshTokens();
        System.out.println("myToken = " + myToken);

        Species species = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies = speciesRepository.save(species);
        Pet pet = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.ACTIVE)
                .petName("몬뭉이")
                .gender(Gender.F)
                .species(findSpecies)
                .petBirth(LocalDate.of(2020,1,1))
                .petFavs("낮잠")
                .petDesc(
                        "삶은 단순하고 아름다울 때,\n" +
                                "간단한 산책이 세상을 매료시킨다.\n" +
                                "눈부신 햇살, 싱그러운 바람,\n" +
                                "삶의 아름다움은 작은 순간에 담겨 있다.")
                .petProfileUrl("dfdf").build();
        Pet pett = petRepository.save(pet);

        //맴버2- member, species, pet//////////////////////
        Member member1 = Member.builder()
                .username("this")
                .password(passwordEncoder.encode("gdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae6@gmail.com")
                .phoneNum(passwordEncoder.encode("2222888888"))
                .roles("ROLE_USER")
                .activated(true)
                .build();
        Member findmember1 = memberRepository.save(member1);
        Species species1 = Species.builder()
                .largeCategory("포유류")
                .midCategory("고양이")
                .smallCategory("삼냥이")
                .build();
        Species findSpecies1 = speciesRepository.save(species1);
        Pet pet1 = Pet.builder()
                .member(findmember1)
                .petStatus(PetStatus.ACTIVE)
                .petName("애옹이")
                .gender(Gender.F)
                .species(species1)
                .petBirth(LocalDate.of(2022,01,29))
                .petFavs("깊은잠")
                .petDesc(
                        "피곤하당")
                .petProfileUrl("sdfdgsg").build();
        petRepository.save(pet1);
        //맴버3- member, species, pet//////////////////////
        Member member3 = Member.builder()
                .username("third")
                .password(passwordEncoder.encode("gdasgsdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae7@gmail.com")
                .phoneNum(passwordEncoder.encode("222s2888888"))
                .roles("ROLE_USER")
                .activated(true)
                .build();
        Member findmember3 = memberRepository.save(member3);
        Species species3 = Species.builder()
                .largeCategory("포유류")
                .midCategory("고양이")
                .smallCategory("삼냥이")
                .build();
        Species findSpecies3 = speciesRepository.save(species3);
        Pet pet3 = Pet.builder()
                .member(findmember3)
                .petStatus(PetStatus.ACTIVE)
                .petName("멍뭉이")
                .gender(Gender.M)
                .species(species1)
                .petBirth(LocalDate.of(2024,01,29))
                .petFavs("singing")
                .petDesc(
                        "meep")
                .petProfileUrl("whatthe").build();
        petRepository.save(pet3);


    }
}
