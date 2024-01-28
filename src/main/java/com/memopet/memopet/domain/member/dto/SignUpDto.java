package com.memopet.memopet.domain.member.dto;

import com.memopet.memopet.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String email;

}
