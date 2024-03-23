package com.memopet.memopet.domain.pet.controller;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.dto.BlockRequestDto;
import com.memopet.memopet.domain.pet.dto.BlockListWrapper;
import com.memopet.memopet.domain.pet.dto.BlockeResponseDto;
import com.memopet.memopet.domain.pet.entity.*;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.domain.pet.service.BlockedService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/block")
@Validated
public class BlockedController {
    private final PetRepository petRepository;
    private final BlockedService blockedService;
    private final MemberRepository memberRepository;
    private final SpeciesRepository speciesRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 차단 리스트
     */
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/{petId}")
    public BlockListWrapper blockedPetList(@PageableDefault(size = 20,page = 0) Pageable pageable, @PathVariable @Param("blockerPetId") Long petId) {
        return blockedService.blockedPetList(pageable,petId);
    }

    /**
     * 차단
     */
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PostMapping("")
    public BlockeResponseDto BlockAPet(@RequestBody @Valid BlockRequestDto blockRequestDTO) {
        return blockedService.blockApet(blockRequestDTO);
    }

    /**
     * 차단 취소
     */
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @DeleteMapping("/{petId}/{blockedPetId}")
    public BlockeResponseDto CancelBlocking(@PathVariable @Param("blockerPetId")Long petId, @PathVariable Long blockedPetId) {
        return blockedService.unblockAPet(petId, blockedPetId);
    }

    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae1@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .roles("ROLE_USER")
                .activated(true)
                .memberStatus(MemberStatus.NORMAL)
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
                .petStatus(PetStatus.DEACTIVE)
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
        petRepository.save(pet);
        //second pet
        Species species12 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies12 = speciesRepository.save(species12);
        Pet pet12 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("참순이1")
                .gender(Gender.F)
                .species(findSpecies12)
                .petBirth(LocalDate.of(2000,1,1))
                .petFavs("낮잠")
                .petDesc(
                        "우리 새침쟁이 참순이")
                .petProfileUrl("meeeeep!!").build();
        petRepository.save(pet12);
        //third pet
        Species species13 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies13 = speciesRepository.save(species13);
        Pet pet13 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("참순이2")
                .gender(Gender.F)
                .species(findSpecies13)
                .petBirth(LocalDate.of(2000,1,1))
                .petFavs("낮잠")
                .petDesc(
                        "우리 새침쟁이 참순이")
                .petProfileUrl("meeeeep!!").build();
        petRepository.save(pet13);
        //forth pet
        Species species14 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies14 = speciesRepository.save(species14);
        Pet pet14 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.DEACTIVE)
                .petName("참순이3")
                .gender(Gender.F)
                .species(findSpecies14)
                .petBirth(LocalDate.of(2000,1,1))
                .petFavs("낮잠")
                .petDesc(
                        "우리 새침쟁이 참순이")
                .petProfileUrl("meeeeep!!").build();
        petRepository.save(pet14);
        //fifth pet
        Species species15 = Species.builder()
                .largeCategory("포유류")
                .midCategory("개")
                .smallCategory("치와와")
                .build();
        Species findSpecies15 = speciesRepository.save(species15);
        Pet pet15 = Pet.builder()
                .member(findmember)
                .petStatus(PetStatus.ACTIVE)
                .petName("참순이4")
                .gender(Gender.F)
                .species(findSpecies15)
                .petBirth(LocalDate.of(2000,1,1))
                .petFavs("낮잠")
                .petDesc(
                        "우리 새침쟁이 참순이")
                .petProfileUrl("meeeeep!!").build();
        petRepository.save(pet15);


        //맴버2- member, species, pet//////////////////////
        Member member1 = Member.builder()
                .username("this")
                .password(passwordEncoder.encode("gdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae2@gmail.com")
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
                .email("jae3@gmail.com")
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
                .species(findSpecies3)
                .petBirth(LocalDate.of(2024,01,29))
                .petFavs("singing")
                .petDesc(
                        "meep")
                .petProfileUrl("whatthe").build();
        petRepository.save(pet3);
    }

}
