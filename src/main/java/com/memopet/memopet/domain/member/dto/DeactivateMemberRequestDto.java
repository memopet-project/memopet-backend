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
public class DeactivateMemberRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @JsonProperty("deactivation_reason")
    private String deactivationReason;

    @JsonProperty("deactivation_reason_comment")
    private String deactivationReasonComment;

}
