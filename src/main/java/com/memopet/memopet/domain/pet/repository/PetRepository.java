package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Likes;
import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value="select * from pet where pet_id NOT IN (:petIds) and deleted_date IS NULL", nativeQuery = true)
    List<Pet> findByIdNotIn(@Param("petIds") Set<Long> petIds);

    @Query(value="select * from pet where pet_id in ?1 and deleted_date IS NULL", nativeQuery = true)
    List<Pet> findByIds(Set<Long> petList);
}
