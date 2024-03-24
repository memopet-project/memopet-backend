package com.memopet.memopet.domain.pet.controller;


import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetController {

    private final PetService petService;
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PostMapping(value="/pet/new",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SavedPetResponseDto savePet(@RequestPart(value="back_img_url") MultipartFile backImgUrl, @RequestPart(value="pet_profile_url") MultipartFile petProfileUrl, @RequestPart(value = "petRequestDto") @Valid SavedPetRequestDto petRequestDto) throws IOException {
        System.out.println("save pet start");
        System.out.println(backImgUrl);
        System.out.println(petProfileUrl);
        System.out.println(petRequestDto.getEmail());
        System.out.println(petRequestDto.getPetDesc());
        System.out.println(petRequestDto.getPetName());
        System.out.println(petRequestDto.getPetSpecM());
        System.out.println(petRequestDto.getPetSpecS());
        System.out.println(petRequestDto.getPetGender());
        System.out.println(petRequestDto.getBirthDate());
        System.out.println(petRequestDto.getPetDeathDate());
        System.out.println(petRequestDto.getPetFavs());
        System.out.println(petRequestDto.getPetFavs2());
        System.out.println(petRequestDto.getPetFavs3());
        System.out.println("-----------------------------------------------------");
        boolean isSaved = petService.savePet(backImgUrl, petProfileUrl, petRequestDto);
        System.out.println("pet saved complete1");
        SavedPetResponseDto petResponse = SavedPetResponseDto.builder().decCode(isSaved ? '1': '0').build();
        return petResponse;
    }

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/pets")
    public PetsResponseDto findPets(PetsRequestDto petsRequestDto) {
        PetsResponseDto petResponseDto = petService.findPetsByPetId(petsRequestDto);
        return petResponseDto;
    }

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/profile-detail")
    public PetDetailInfoResponseDto findPetDetailInfo(PetDetailInfoRequestDto petDetailInfoRequestDto) {
        PetDetailInfoResponseDto petDetailInfoResponseDto  = petService.findPetDetailInfo(petDetailInfoRequestDto);
        return petDetailInfoResponseDto;
    }

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PatchMapping("/profile")
    public PetUpdateInfoResponseDto findPets(PetUpdateInfoRequestDto petUpdateInfoRequestDto) {
        PetUpdateInfoResponseDto petUpdateInfoResponseDto  = petService.updatePetInfo(petUpdateInfoRequestDto);
        return petUpdateInfoResponseDto;
    }
}
