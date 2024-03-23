package com.memopet.memopet.domain.pet.controller;


import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PetController {

    private final PetService petService;


    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @PostMapping(value = "/pet/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PetResponseDto savePet(HttpServletRequest request, @RequestPart(value = "back_img_url") MultipartFile backImgUrl, @RequestPart(value = "pet_profile_url") MultipartFile petProfileUrl , @RequestPart(value = "petRequestDto") @Valid PetRequestDto petRequestDto) throws IOException {
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
        PetResponseDto petResponse = PetResponseDto.builder().decCode(isSaved ? '1' : '0').build();
        return petResponse;
    }

    /**
     * 내 프로필 리스트
     */
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/pet/profiles/{petId}")
    public PetListWrapper petsList(@PageableDefault(size = 5, page = 0) Pageable pageable, @PathVariable Long petId) {
        return petService.profileList(pageable, petId);
    }

    /**
     * 펫 프로필 전환
     */
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PatchMapping("/pet")
    public PetResponseDto switchProfile(@RequestBody PetSwitchRequestDto petSwitchResponseDTO) {
        boolean isSwitched = petService.switchProfile(petSwitchResponseDTO);
        return PetResponseDto.builder().decCode(isSwitched ? '1' : '0').build();

    }

    /**
     * 펫 프로필 삭제
     */

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PostMapping("/pet")
    public PetResponseDto deletePetProfile(@RequestBody PetDeleteRequestDto petDeleteRequestDTO) {
        boolean isDeleted=petService.deletePetProfile(petDeleteRequestDTO);
        return new PetResponseDto(isDeleted ? '1' : '0');
    }
}
