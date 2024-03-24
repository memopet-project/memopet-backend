package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemoryPostResponseDto {
    @JsonProperty("dec_code")
    private char decCode;
}
