package com.memopet.memopet.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyIdRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    @JsonProperty("phone_num")
    private String phoneNum;
}
