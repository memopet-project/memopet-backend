package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetAndMemoryCommentsRequestDto {
    @JsonProperty("pet_id")
    private Long petId;
    @JsonProperty("memory_id")
    private Long memoryId;
    @JsonProperty("comment_id")
    private Long commentId;
    @JsonProperty("depth")
    private int depth;
    @JsonProperty("comment_group")
    private int commentGroup;
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("data_counts")
    private int dataCounts;
}
