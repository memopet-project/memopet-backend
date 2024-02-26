package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Follow;
import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> , CustomFollowRepository {
        @Query("SELECT CASE WHEN EXISTS (SELECT f FROM Follow f WHERE f.petId = :petId AND f.followingPet.id = :followingPetId) THEN true ELSE false END")
        boolean existsByPetIdAndFollowingPetId(@Param("petId") Long petId, @Param("followingPetId") Long followingPetId);

//        @Query("DELETE FROM Follow f where f.petId = :petId AND f.followingPet.id = :followingPetId")
        @Transactional
        void deleteByPetIdAndFollowingPetId(@Param("petId") Long petId, @Param("followingPetId") Long followingPetId);
}
