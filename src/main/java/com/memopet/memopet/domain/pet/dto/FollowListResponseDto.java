package com.memopet.memopet.domain.pet.dto;

import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
}
