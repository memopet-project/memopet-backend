package com.memopet.memopet.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeactivateMemberResponseDto {

    @JsonProperty("dsc_code")
    private String dscCode;

    @JsonProperty("err_message")
    private String errMessage;
}
