package com.memopet.memopet.domain.pet.dto;

import com.memopet.memopet.domain.pet.entity.Pet;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlockedListDTO {
    @NotNull
    private Long petId;

    @NotNull
    private String petName;

    @NotNull
    private String petProfileUrl;

    @NotNull
    private String petDesc;


    public BlockedListDTO(Long petId, String petName, String petProfileUrl, String petDesc) {
        this.petId = petId;
        this.petName = petName;
        this.petProfileUrl = petProfileUrl;
        this.petDesc = petDesc;
    }

    public BlockedListDTO(Pet pet) {
        petId = pet.getId();
        petName = pet.getPetName();
        petProfileUrl = pet.getPetProfileUrl();
        petDesc = pet.getPetDesc();
    }
}
