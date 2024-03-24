package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportPostRequestDto {

    @JsonProperty("report_category")
    private String reportCategory;
    @JsonProperty("report_reason")
    private String reportReason;
    @JsonProperty("comment_id")
    private Long commentId;
    private Long reporter; // 신고한 프로필 (pet_id)
    private Long reported; // 신고당한 프로필 (pet_id)

}
