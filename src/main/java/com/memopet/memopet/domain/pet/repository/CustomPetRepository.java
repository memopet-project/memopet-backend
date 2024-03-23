package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.PetListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomPetRepository {
    Page<PetListResponseDto> findPetsById(Pageable pageable, Long id);

    boolean switchPetProfile(Long petId);

    boolean deleteAPet(UUID memberId, Long petId);
}
