package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Follow;
import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {



    @Query("select f from Follow f where f.pet = :petId")
    List<Follow> findByPetId(@Param("petId") Pet petId);

    @Query("select f from Follow f where f.pet = :petId and f.myPetId = :myPetId")
    Optional<Follow> findByPetIdAndMyPetId(@Param("petId") Pet pet, Long myPetId);
}
