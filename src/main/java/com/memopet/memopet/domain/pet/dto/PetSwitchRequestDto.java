package com.memopet.memopet.domain.pet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PetSwitchRequestDto {
    Long petId;

    public PetSwitchRequestDto(Long petId) {
        this.petId = petId;
    }

}
