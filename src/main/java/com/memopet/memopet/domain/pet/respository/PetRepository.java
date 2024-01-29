package com.memopet.memopet.domain.pet.respository;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findById(Long id);


}
