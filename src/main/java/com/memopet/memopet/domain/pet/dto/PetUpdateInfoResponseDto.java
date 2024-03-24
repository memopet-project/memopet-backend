package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetUpdateInfoResponseDto {

    @JsonProperty("dec_code")
    private char decCode;
    @JsonProperty("err_msg")
    private String errMsg;
}
