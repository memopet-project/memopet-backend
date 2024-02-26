package com.memopet.memopet.domain.pet.dto;

import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@QueryEntity
public class FollowListResponseDto {
    @NotNull
    private Long petId;

    @NotNull
    private String petName;

    @NotNull
    private String petProfileUrl;

    @NotNull
    private String petDesc;

    public FollowListResponseDto(Long petId, String petName, String petProfileUrl, String petDesc) {
        this.petId = petId;
        this.petName = petName;
        this.petProfileUrl = petProfileUrl;
        this.petDesc = petDesc;
    }
}
