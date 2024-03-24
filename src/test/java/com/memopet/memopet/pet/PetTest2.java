package com.memopet.memopet.pet;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.controller.PetController;
import com.memopet.memopet.domain.pet.dto.PetDeleteRequestDto;
import com.memopet.memopet.domain.pet.dto.PetListResponseDto;
import com.memopet.memopet.domain.pet.dto.PetListWrapper;
import com.memopet.memopet.domain.pet.dto.PetResponseDto;
import com.memopet.memopet.domain.pet.entity.Gender;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.PetStatus;
import com.memopet.memopet.domain.pet.entity.Species;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.domain.pet.service.PetService;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

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

    @PostConstruct
    private void init() {

        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae1122@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .roles("ROLE_USER")
                .activated(true)
                .build();
        Member findmember = memberRepository.save(member);


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
                .petBirth(LocalDate.of(2020, 1, 1))
                .petFavs("낮잠")
                .petDesc(
                        "삶은 단순하고 아름다울 때,\n" +
                                "간단한 산책이 세상을 매료시킨다.\n" +
                                "눈부신 햇살, 싱그러운 바람,\n" +
                                "삶의 아름다움은 작은 순간에 담겨 있다.")
                .petProfileUrl("dfdf").build();
        petRepository.save(pet);
        //second pet-참순이
        Species species2 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies2 = speciesRepository.save(species2);
        Pet pet2 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("참돌이")
                .gender(Gender.M)
                .species(findSpecies2)
                .petBirth(LocalDate.of(2020, 1, 1))
                .petFavs("장난치기")
                .petDesc(
                        "니가 손을 달라고 한다면 나는 네게 내 뒷다리를 주겠당.")
                .petProfileUrl("dfdf").build();
        petRepository.save(pet2);

        //third pet-참돌이
        Species species3 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("진돗개")
                .build();
        Species findSpecies3 = speciesRepository.save(species3);
        Pet pet3 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("참순이")
                .gender(Gender.M)
                .species(findSpecies3)
                .petBirth(LocalDate.of(2020, 1, 1))
                .petFavs("낮잠")
                .petDesc(
                        "으르렁!!!")
                .petProfileUrl("dfdf").build();
        petRepository.save(pet3);
        // pet4-미피
        Species species4 = Species.builder()
                .largeCategory("포유류")
                .midCategory("토끼")
                .smallCategory("흰토끼")
                .build();
        Species findSpecies4 = speciesRepository.save(species3);
        Pet pet4 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("미피")
                .gender(Gender.M)
                .species(findSpecies3)
                .petBirth(LocalDate.of(2024, 1, 1))
                .petFavs("먹방")
                .petDesc(
                        "빼액!!!")
                .petProfileUrl("dfdf").build();
        petRepository.save(pet4);
        // pet5-비버
        Species species5 = Species.builder()
                .largeCategory("포유류")
                .midCategory("비버")
                .smallCategory("아마존비버")
                .build();
        Species findSpecies5 = speciesRepository.save(species5);
        Pet pet5 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("루피")
                .gender(Gender.M)
                .species(findSpecies3)
                .petBirth(LocalDate.of(2019, 1, 1))
                .petFavs("화내기")
                .petDesc(
                        "으르렁!!!")
                .petProfileUrl("ㄴㄴㄴㄴ").build();
        petRepository.save(pet5);

        //두번쨰 회윈

        Member member2 = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("22222@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .roles("ROLE_USER")
                .activated(true)
                .build();
        Member findmember22 = memberRepository.save(member2);


        Species species22 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies22 = speciesRepository.save(species22);
        Pet pet22 = Pet.builder()
                .member(findmember22)
                .petStatus(PetStatus.ACTIVE)
                .petName("몬뭉이")
                .gender(Gender.F)
                .species(findSpecies22)
                .petBirth(LocalDate.of(2020, 1, 1))
                .petFavs("낮잠")
                .petDesc(
                        "삶은 단순하고 아름다울 때,\n" +
                                "간단한 산책이 세상을 매료시킨다.\n" +
                                "눈부신 햇살, 싱그러운 바람,\n" +
                                "삶의 아름다움은 작은 순간에 담겨 있다.")
                .petProfileUrl("dfdf").build();
        petRepository.save(pet22);
    }
//    @Test
//    public void MyProfileList() throws Exception {
//
//        /**
//         * 내 프로필 리스트 로 가져오기
//         */
//
//
////        CustomPetReposiotryImpl-성공
//        Pageable pageable = PageRequest.of(0, 5);
//        Long petId = 3L;
//        Page<PetListResponseDto> result = petRepository.findPetsById(pageable, petId);
//
//        for (PetListResponseDto petListResponseDTO : result) {
//            System.out.println("petListResponseDTO.getPetName() = " + petListResponseDTO.getPetName());
//            System.out.println("petListResponseDTO.getPetId() = " + petListResponseDTO.getPetId());
//            System.out.println("petListResponseDTO.getPetProfileUrl() = " + petListResponseDTO.getPetProfileUrl());
//        }
//        Assertions.assertThat(result.getTotalElements()).isEqualTo(5);
////            petService에서 확인하기
//        PetListWrapper result2 = petService.profileList(pageable, petId);
//        System.out.println("result = " + result2);
//        for (PetListResponseDto petListResponseDTO : result2.getPetList()) {
//            System.out.println("petListResponseDTO.getPetProfileUrl() = " + petListResponseDTO.getPetProfileUrl());
//            System.out.println("petListResponseDTO.getPetId() = " + petListResponseDTO.getPetId());
//            System.out.println("petListResponseDTO.getPetName() = " + petListResponseDTO.getPetName());
//        }
//        Assertions.assertThat(result2.getPetList().getTotalElements()).isEqualTo(5);
//
//
//
//    }

    @Test
    public void switchProfile() throws Exception {

        Long petId = 1L;
        Assertions.assertThat(petRepository.switchPetProfile(petId)).isTrue();
        //이미 활성화된 계정이거나 아예 존재하지 않는 펫아이디일떄.
        Long petId2 = 100L;
        Assertions.assertThat(petRepository.switchPetProfile(petId2)).isFalse();

    }

//    @Test
//    public void deleteAPet() throws Exception {
//        /**
//         * 프로필 삭제
//         */
//        Optional<Member> member = memberRepository.findOneWithAuthoritiesByEmail("22222@gmail.com");
//        Assertions.assertThat(member.isPresent()).isTrue();
//        PetDeleteRequestDto petDeleteRequestDto = new PetDeleteRequestDto(member.get().getPets().get(0).getId(), "22222@gmail.com", "Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23");
//        PetResponseDto petResponseDto = petService.deletePetProfile(petDeleteRequestDto);
//        Assertions.assertThat(petResponseDto.getDecCode()).isEqualTo('1');
//        System.out.println("petResponseDto.getMessage() = " + petResponseDto.getMessage());
//        System.out.println("petResponseDto.getDecCode() = " + petResponseDto.getDecCode());
//
//    }

}
