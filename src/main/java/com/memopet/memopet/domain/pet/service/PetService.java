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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PetService {
    // 의존성 주입
    private final PetRepository petRepository;
    private final SpeciesRepository speciesRepository;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;


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

    @Transactional(readOnly = true)
    public PetListWrapper profileList(Pageable pageable, PetListRequestDTO petListRequestDTO) {
        PetListWrapper wrapper = new PetListWrapper();
        if (petListRequestDTO == null || petListRequestDTO.getEmail() == null || petListRequestDTO.getEmail().isEmpty()) {
            // Set error code and description for missing or invalid email
            wrapper.setErrorCode("0");
            wrapper.setErrorDescription("Email is missing or invalid");
        } else {
        wrapper.setPetList(petRepository.findPetsByEmail(pageable,petListRequestDTO.getEmail()));}
        return wrapper;
    }
}
