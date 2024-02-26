package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.FollowListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomFollowRepository {
    Page<FollowListResponseDto> findFollowingPetsById(Pageable pageable,Long petId);
    Page<FollowListResponseDto> findFollowerPetsByPetId(Pageable pageable,Long petId);
}
