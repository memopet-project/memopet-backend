package com.memopet.memopet.domain.pet.controller;


import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.service.MemoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemoryController {

    private final MemoryService memoryService;

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/memory")
    public MemoryResponseDto memory(MemoryRequestDto memoryRequestDto) {
        MemoryResponseDto memoryResponseDto = memoryService.findMemoryByMemoryId(memoryRequestDto.getMemoryId());
        return memoryResponseDto;
    }
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/liked-memories")
    public LikedMemoryResponseDto likedMemories(LikedMemoryRequestDto likedMemoryRequestDto) {
        LikedMemoryResponseDto likedMemoryResponseDto = memoryService.findLikedMemoriesByPetId(likedMemoryRequestDto);
        return likedMemoryResponseDto;
    }

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/main-memories")
    public LikedMemoryResponseDto mainMemories(LikedMemoryRequestDto likedMemoryRequestDto) {
        LikedMemoryResponseDto likedMemoryResponseDto = memoryService.findMainMemoriesByPetId(likedMemoryRequestDto);
        return likedMemoryResponseDto;
    }


    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/month-memories")
    public MonthMemoriesResponseDto monthMemories(MonthMemoriesRequestDto monthMemoriesRequestDto) {
        MonthMemoriesResponseDto monthMemoriesResponseDto = memoryService.findMonthMemoriesByPetId(monthMemoriesRequestDto);
        return monthMemoriesResponseDto;
    }

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @DeleteMapping("/memory")
    public MemoryDeleteResponseDto deleteMemory(@RequestBody MemoryDeleteRequestDto memoryDeleteRequestDto) {
        MemoryDeleteResponseDto memoryDeleteResponseDto  = memoryService.deleteMemory(memoryDeleteRequestDto);
        return memoryDeleteResponseDto;
    }

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PatchMapping(value="/memory")
    public MemoryUpdateResponseDto updateMemory(@RequestPart List<MultipartFile> files, @RequestPart(value = "memoryUpdateRequestDto") @Valid MemoryUpdateRequestDto memoryUpdateRequestDto) {
        MemoryUpdateResponseDto memoryUpdateResponseDto = memoryService.updateMemoryInfo(memoryUpdateRequestDto, files);
        return memoryUpdateResponseDto;
    }
}
