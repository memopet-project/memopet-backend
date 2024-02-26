package com.memopet.memopet.domain.pet.dto;

import lombok.Data;

@Data
public class SwitchProfileDTO {
    Long id;

    public SwitchProfileDTO(Long petId) {
        this.id = petId;
    }
}
