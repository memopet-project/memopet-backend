package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.MemoryResponseDto;
import com.memopet.memopet.domain.pet.entity.Memory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    Optional<Memory> findByTitle(String title);
    @Override
    Optional<Memory> findById(Long memory_id);
    Page<Memory> findMemoriesByPetId(Long petId, Pageable pageable);
    @Query(value = "select * from Memory where memory_id in ?1", nativeQuery = true)
    List<Memory> findByMemoryIds(List<Long> memory_ids);
}
