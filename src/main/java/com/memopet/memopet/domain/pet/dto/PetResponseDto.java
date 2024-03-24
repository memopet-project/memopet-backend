package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.memopet.memopet.domain.pet.entity.Gender;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDto {

    @JsonProperty("pet_id")
    private Long petId;
    @JsonProperty("pet_nm")
    private String petName;
    @JsonProperty("pet_desc")
    private String petDesc;
    @JsonProperty("pet_gender")
    private Gender petGender;
    @JsonProperty("pet_profile_url")
    private String petProfileUrl;
    @JsonProperty("back_img_url")
    private String backImgUrl;
    @JsonProperty("likes")
    private int likes;

}


