package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.dto.MemoryUpdateRequestDto;

import java.util.List;

public interface CustomMemoryRepository {
    void deleteAllMemories(List<Long> petIds);
    void updateMemoryInfo(MemoryUpdateRequestDto memoryUpdateRequestDto);
}
