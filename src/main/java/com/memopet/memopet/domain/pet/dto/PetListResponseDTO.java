package com.memopet.memopet.domain.pet.dto;

import lombok.Data;

@Data
public class PetListResponseDTO {
    private Long petId;

    private String petName;
    private String petProfileUrl;
    public PetListResponseDTO(Long petId, String petName, String petProfileUrl) {
        this.petId = petId;
        this.petName = petName;
        this.petProfileUrl = petProfileUrl;
    }
}
