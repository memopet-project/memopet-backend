package com.memopet.memopet.domain.pet.service;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.PetStatus;
import com.memopet.memopet.domain.pet.entity.Species;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.global.common.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {
    // 의존성 주입
    private final PetRepository petRepository;
    private final SpeciesRepository speciesRepository;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = false)
    public boolean savePet(MultipartFile petImg, MultipartFile backgroundImg, PetRequestDto petRequestDto) throws IOException {
        boolean isSaved = false;
        String storedPetImgName = null;


        System.out.println("savePet service start");


        if (!petImg.isEmpty()) {
            System.out.println("savePet s3Uploader upload start");
            storedPetImgName = s3Uploader.uploadFileToS3(petImg, "static/team-image");
        }
        String storedBackgroundImgName = null;
        if (!backgroundImg.isEmpty()) {
            storedBackgroundImgName = s3Uploader.uploadFileToS3(backgroundImg, "static/team-image");
        }
        System.out.println(storedPetImgName);
        System.out.println(storedBackgroundImgName);
        Species species = Species.builder().largeCategory("포유류").midCategory(petRequestDto.getPetSpecM()).smallCategory(petRequestDto.getPetSpecS()).build();
        Species savedSpecies = speciesRepository.save(species);
        Member member = memberRepository.findByEmail(petRequestDto.getEmail());


        Pet pet = Pet.builder()
                .petName(petRequestDto.getPetName())
                .gender(petRequestDto.getPetGender())
                .petDesc(petRequestDto.getPetDesc())
                .member(member)
                .species(savedSpecies)
                .petBirth(LocalDate.parse(petRequestDto.getBirthDate(), DateTimeFormatter.ISO_DATE))
                .petDeathDate(LocalDate.parse(petRequestDto.getPetDeathDate(), DateTimeFormatter.ISO_DATE))
                .petFavs(petRequestDto.getPetFavs())
                .petFavs2(petRequestDto.getPetFavs2())
                .petFavs3(petRequestDto.getPetFavs3())
                .petProfileUrl(storedPetImgName)
                .backImgUrl(storedBackgroundImgName)
                .petStatus(PetStatus.ACTIVE)
                .build();

        System.out.println("pet build complete");
        Pet savedPet = petRepository.save(pet);
        System.out.println("pet saved complete");
        return isSaved;
    }

    /**
     * 내 프로필 리스트
     */
    @Transactional(readOnly = true)
    public PetListWrapper profileList(Pageable pageable, Long petId) {

        if (petId == null) {
            // Set error code and description for missing or invalid email
           return PetListWrapper.builder().decCode('0').build();

        } else {
            return PetListWrapper.builder().petList(petRepository.findPetsById(pageable,petId)).build();}

    }

    /**
     * 펫 프로필 전환
     */
    @Transactional(readOnly = false)
    public boolean switchProfile(PetSwitchRequestDto petSwitchRequestDTO) {

        return petRepository.switchPetProfile(petSwitchRequestDTO.getPetId());
    }

    @Transactional(readOnly = false)
    public PetResponseDto deletePetProfile(PetDeleteRequestDto petDeleteRequestDTO) {try {
        Optional<Member> member = memberRepository.findOneWithAuthoritiesByEmail(petDeleteRequestDTO.getEmail());
        if (member.isEmpty()) {
            return new PetResponseDto('0',"존재하지 않거나 삭제된 이메일입니다"); // Member not found
        }

        if (!passwordEncoder.matches(petDeleteRequestDTO.getPassword(), member.get().getPassword())) {
            return new PetResponseDto('0',"비밀번호를 다시 입력하세요.");
        }

        // Attempt to delete the pet profile
        boolean deletionSuccessful = petRepository.deleteAPet(member.get().getId(), petDeleteRequestDTO.getPetId());

        if (!deletionSuccessful) {
            return new PetResponseDto('0',"프로필 삭제를 실패했습니다.");
        }

        return new PetResponseDto('1',"프로필이 삭제되었습니다.");
    } catch (Exception e) {
        return new PetResponseDto('0',"프로필 삭제를 실패하였습니다.");
    }

    }
}
