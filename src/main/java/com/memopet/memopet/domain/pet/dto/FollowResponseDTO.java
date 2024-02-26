package com.memopet.memopet.domain.pet.dto;

import lombok.Data;

@Data
public class FollowResponseDTO {
    private int resultCode;
    private String message;

    public FollowResponseDTO(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
}
