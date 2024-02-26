package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.PetListResponseDTO;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.QPet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPetRepository {
    Page<PetListResponseDTO> findPetsByEmail(Pageable pageable, String email);

    public List<Pet> findMyPets(Long PetId);
}
