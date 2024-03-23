package com.memopet.memopet.domain.pet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowListRequestDto {
    private Long petId;
    private int followType; //리스트 조회- 1:팔로워 2:팔로우

    public FollowListRequestDto(Long petId, int followType) {
        this.petId = petId;
        this.followType = followType;
    }
}
