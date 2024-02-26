package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> ,CustomPetRepository{
    List<Pet> findByIdIn(List<Long> blockedPetIds);

//    Optional<Pet> findById(Long id);


}
