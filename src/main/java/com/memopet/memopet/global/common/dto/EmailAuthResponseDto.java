package com.memopet.memopet.global.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailAuthResponseDto {
    @JsonProperty("dsc_code")
    private String dscCode;

    @JsonProperty("auth_code")
    private String authCode;

    @JsonProperty("err_message")
    private String errMessage;
}
