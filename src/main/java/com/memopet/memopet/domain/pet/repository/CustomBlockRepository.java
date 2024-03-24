package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.BlockedListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface CustomBlockRepository {
        Page<BlockedListResponseDto> findBlockedPets(@Param("blockerPetId") Long blockerPetId, Pageable pageable);
}
