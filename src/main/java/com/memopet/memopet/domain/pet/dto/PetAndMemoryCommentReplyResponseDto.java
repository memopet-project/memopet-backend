package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetAndMemoryCommentReplyResponseDto {

    @JsonProperty("pet_id")
    private Long petId;
    @JsonProperty("pet_nm")
    private String petName;
    @JsonProperty("pet_profile_url")
    private String petProfileUrl;
    @JsonProperty("comment_id")
    private Long commentId;
    @JsonProperty("commenter_id")
    private Long commenterId;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("comment_created_date")
    private LocalDateTime commentCreatedDate;
}
