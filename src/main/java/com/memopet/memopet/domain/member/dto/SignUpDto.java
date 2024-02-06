package com.memopet.memopet.domain.member.dto;

import com.memopet.memopet.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotEmpty(message = "User Name must not be empty")
    private String username;
    private String password;
    @NotEmpty(message = "User email must not be empty") //Neither null nor 0 size
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "phoneNum must not be empty")
    private String phoneNum;
}
