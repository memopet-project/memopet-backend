package com.memopet.memopet.domain.pet.dto;

import lombok.Data;

@Data
public class FollowDTO {
    private Long petId;
    private Long followingPetId;

    public FollowDTO(Long petId, Long followingPetId) {
        this.petId = petId;
        this.followingPetId = followingPetId;
    }
}
