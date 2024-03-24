package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarmCommentUpdateRequestDto {

    @JsonProperty("comment_id")
    private Long commentId;
    private String comment;
}
