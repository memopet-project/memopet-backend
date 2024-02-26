package com.memopet.memopet.domain.pet.dto;

import lombok.Data;

@Data
public class FollowerDTO {
    private Long petId;
    private Long followingPetId;

    public FollowerDTO(Long petId, Long followerPetId) {
        this.petId = followerPetId;
        this.followingPetId = petId;
    }
}
