package com.memopet.memopet.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponseDto {

    @JsonProperty("dsc_code")
    private String dscCode;
    private String email;
    private String username;
    @JsonProperty("phone_num")
    private String phoneNum;
    @JsonProperty("err_message")
    private String errMessage;

}
