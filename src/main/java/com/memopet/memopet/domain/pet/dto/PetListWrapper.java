package com.memopet.memopet.domain.pet.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PetListWrapper {
    private Page<PetListResponseDTO> petList;
    private String errorCode;
    private String errorDescription;

    public PetListWrapper(Page<PetListResponseDTO> petList, String errorCode, String errorDescription) {
        this.petList = petList;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public PetListWrapper() {

    }
}
