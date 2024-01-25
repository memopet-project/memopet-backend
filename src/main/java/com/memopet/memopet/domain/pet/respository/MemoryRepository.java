package com.memopet.memopet.domain.pet.respository;

import com.memopet.memopet.domain.pet.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
}
