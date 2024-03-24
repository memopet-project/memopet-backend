package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDetailInfoRequestDto {
    @JsonProperty("pet_id")
    private Long petId;
    @JsonProperty("my_pet_id")
    private Long myPetId;
}
