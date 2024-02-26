package com.memopet.memopet.domain.pet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetListRequestDTO {
    private String email;

    public PetListRequestDTO(String email) {
        this.email = email;
    }
}
