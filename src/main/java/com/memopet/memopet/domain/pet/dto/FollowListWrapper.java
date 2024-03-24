package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

@Builder
@Getter
public class FollowListWrapper {
    private Page<FollowListResponseDto> followList;
    @JsonProperty("dec_code")
    private char decCode;
    private String errorDescription;
}
