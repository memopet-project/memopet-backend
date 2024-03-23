package com.memopet.memopet.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoRequestDto {

    @NotEmpty(message = "dsc_code must not be empty")
    @JsonProperty("dsc_code")
    private int dscCode;
    @NotEmpty(message = "Email must not be empty")
    private String email;
    private String username;
    private String password;
    @JsonProperty("phone_num")
    private String phoneNum;

}
