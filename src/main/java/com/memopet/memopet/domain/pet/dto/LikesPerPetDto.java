package com.memopet.memopet.domain.pet.dto;

import com.memopet.memopet.domain.pet.entity.Pet;
import lombok.Getter;
import lombok.Setter;


public interface LikesPerPetDto {

    long getPetId();
    Integer getLikes();

}
