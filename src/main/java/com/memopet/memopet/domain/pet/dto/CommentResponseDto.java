package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    @JsonProperty("memory_image_url_id_1")
    private Long memoryImageUrlId1;
    @JsonProperty("memory_image_url_1")
    private String memoryImageUrl1;
    @JsonProperty("memory_id")
    private Long memoryId;
    @JsonProperty("comment_id")
    private Long commentId;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("comment_created_date")
    private LocalDateTime commentCreatedDate;

}
