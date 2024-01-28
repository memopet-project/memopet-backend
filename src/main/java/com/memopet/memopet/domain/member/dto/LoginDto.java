package com.memopet.memopet.domain.member.dto;

import com.memopet.memopet.domain.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String Email;
    private String password;

}
