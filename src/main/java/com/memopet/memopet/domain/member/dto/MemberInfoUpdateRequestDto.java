package com.memopet.memopet.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoUpdateRequestDto {

    @NotEmpty(message = "Email must not be empty")
    private String email;
    private String username;
    private String phoneNum;
}
