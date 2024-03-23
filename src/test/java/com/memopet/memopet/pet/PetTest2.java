package com.memopet.memopet.pet;

import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.controller.PetController;
import com.memopet.memopet.domain.pet.dto.PetListResponseDto;
import com.memopet.memopet.domain.pet.dto.PetListWrapper;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.domain.pet.service.PetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
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
    PetController petController;

    @Autowired
    PetService petService;





    @Test
    public void MyProfileList() throws Exception {
//        CustomPetReposiotryImpl-성공
        Pageable pageable = PageRequest.of(0, 5);
        Long petId=3L;
        Page<PetListResponseDto> result=petRepository.findPetsById(pageable,petId);

        for (PetListResponseDto petListResponseDTO : result) {
            System.out.println("petListResponseDTO.getPetName() = " + petListResponseDTO.getPetName());
            System.out.println("petListResponseDTO.getPetId() = " + petListResponseDTO.getPetId());
            System.out.println("petListResponseDTO.getPetProfileUrl() = " + petListResponseDTO.getPetProfileUrl());
        }
        Assertions.assertThat(result.getTotalElements()).isEqualTo(5);
//            petService에서 확인하기
        PetListWrapper result2= petService.profileList(pageable,petId);
        System.out.println("result = " + result2);
        for (PetListResponseDto petListResponseDTO : result2.getPetList()) {
            System.out.println("petListResponseDTO.getPetProfileUrl() = " + petListResponseDTO.getPetProfileUrl());
            System.out.println("petListResponseDTO.getPetId() = " + petListResponseDTO.getPetId());
            System.out.println("petListResponseDTO.getPetName() = " + petListResponseDTO.getPetName());
        }
        Assertions.assertThat(result2.getPetList().getTotalElements()).isEqualTo(5);
    }
    
    @Test
    public void switchProfile() throws Exception {

        Long petId = 6L;
        Assertions.assertThat(petRepository.switchPetProfile(petId)).isTrue();
        //이미 활성화된 계정이거나 아예 존재하지 않는 펫아이디일떄.
        Long petId2 = 8L;
        Assertions.assertThat(petRepository.switchPetProfile(petId2)).isFalse();

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
