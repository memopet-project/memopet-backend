package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    Optional<Memory> findByTitle(String title);
}
