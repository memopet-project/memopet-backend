package com.memopet.memopet.pet;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.QMember;
import com.memopet.memopet.domain.member.entity.RefreshTokenEntity;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.dto.PetListRequestDTO;
import com.memopet.memopet.domain.pet.dto.PetListResponseDTO;
import com.memopet.memopet.domain.pet.dto.PetListWrapper;
import com.memopet.memopet.domain.pet.entity.*;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.domain.pet.service.PetService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class PetTest2 {
    @Autowired
    PetRepository petRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SpeciesRepository speciesRepository;

    @Autowired
    PetService petService;




    @Test
    public void MyProfileList() throws Exception {
        //given
        String email = "jae@gmail.com";
        memberRepository.findByEmail(email);
        Pageable pageable = PageRequest.of(0, 5);
        //when
//        Page<PetListResponseDTO> result= memberRepository.findPetsByEmail(pageable,email);
//
//        for (PetListResponseDTO petListResponseDTO : result) {
//            System.out.println("petListResponseDTO.getPetName() = " + petListResponseDTO.getPetName());
//            System.out.println("petListResponseDTO.getPetId() = " + petListResponseDTO.getPetId());
//            System.out.println("petListResponseDTO.getPetProfileUrl() = " + petListResponseDTO.getPetProfileUrl());
//        }
        PetListRequestDTO email1 = new PetListRequestDTO(email);
        PetListWrapper result= petService.profileList(pageable, email1);
        System.out.println("result = " + result);
        for (PetListResponseDTO petListResponseDTO : result.getPetList()) {
            System.out.println("petListResponseDTO.getPetProfileUrl() = " + petListResponseDTO.getPetProfileUrl());
            System.out.println("petListResponseDTO.getPetId() = " + petListResponseDTO.getPetId());
            System.out.println("petListResponseDTO.getPetName() = " + petListResponseDTO.getPetName());
        }
        


        //then

    }
    
    @Test
    public void switchProfile() throws Exception {
        //given
        Long petId = 1L;
        List<Pet> list = petRepository.findMyPets(petId);
        //when
        
        //then
        for (Pet pet : list) {
            System.out.println("pet.getPetName() = " + pet.getPetName());
            System.out.println("pet.getPetProfileUrl() = " + pet.getPetProfileUrl());
            System.out.println("pet.getId() = " + pet.getId());
        }


    
    }
//    @PostConstruct
//    public class init{
//
//        Member member = Member.builder()
//                .username("Test")
//                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
//                .email("jae@gmail.com")
//                .phoneNum(passwordEncoder.encode("01052888888"))
//                .roles("ROLE_USER")
//                .activated(true)
//                .build();
//        Member findmember = memberRepository.save(member);
//
//        RefreshTokenEntity myToken = (RefreshTokenEntity) findmember.getRefreshTokens();
//        System.out.println("myToken = " + myToken);
//
//        Species species = Species.builder()
//                .largeCategory("포유류")
//                .midCategory("개")
//                .smallCategory("치와와")
//                .build();
//        Species findSpecies = speciesRepository.save(species);
//        Pet pet = Pet.builder()
//                .member(findmember)
//                .petStatus(PetStatus.ACTIVE)
//                .petName("몬뭉이")
//                .gender(Gender.F)
//                .species(findSpecies)
//                .petBirth(LocalDate.of(2020, 1, 1))
//                .petFavs("낮잠")
//                .petDesc(
//                        "삶은 단순하고 아름다울 때,\n" +
//                                "간단한 산책이 세상을 매료시킨다.\n" +
//                                "눈부신 햇살, 싱그러운 바람,\n" +
//                                "삶의 아름다움은 작은 순간에 담겨 있다.")
//                .petProfileUrl("dfdf").build();
//      petRepository.save(pet);
//      //second pet-참순이
//      Species species2 = Species.builder()
//                .largeCategory("포유류")
//                .midCategory("개")
//                .smallCategory("치와와")
//                .build();
//        Species findSpecies2 = speciesRepository.save(species2);
//        Pet pet2 = Pet.builder()
//                .member(findmember)
//                .petStatus(PetStatus.DEACTIVE)
//                .petName("참돌이")
//                .gender(Gender.M)
//                .species(findSpecies2)
//                .petBirth(LocalDate.of(2020, 1, 1))
//                .petFavs("장난치기")
//                .petDesc(
//                        "니가 손을 달라고 한다면 나는 네게 내 뒷다리를 주겠당.")
//                .petProfileUrl("dfdf").build();
//      petRepository.save(pet2);
//
//      //third pet-참돌이
//      Species species3 = Species.builder()
//                .largeCategory("포유류")
//                .midCategory("개")
//                .smallCategory("진돗개")
//                .build();
//        Species findSpecies3 = speciesRepository.save(species3);
//        Pet pet3 = Pet.builder()
//                .member(findmember)
//                .petStatus(PetStatus.DEACTIVE)
//                .petName("참순이")
//                .gender(Gender.M)
//                .species(findSpecies3)
//                .petBirth(LocalDate.of(2020, 1, 1))
//                .petFavs("낮잠")
//                .petDesc(
//                        "으르렁!!!")
//                .petProfileUrl("dfdf").build();
//      petRepository.save(pet3);
//    }
}
