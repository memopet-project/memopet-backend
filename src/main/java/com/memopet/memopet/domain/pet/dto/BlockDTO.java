package com.memopet.memopet.domain.pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlockDTO {
    @NotNull
    Long blockerPetId;
    @NotNull
    Long blockedPetId;

    public BlockDTO(Long petId, Long blockedPet) {
        this.blockerPetId = petId;
        this.blockedPetId = blockedPet;
    }


}
