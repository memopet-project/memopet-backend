package com.memopet.memopet.domain.pet.controller;


import com.memopet.memopet.domain.pet.dto.LikedMemoryRequestDto;
import com.memopet.memopet.domain.pet.dto.LikedMemoryResponseDto;
import com.memopet.memopet.domain.pet.dto.MemoryRequestDto;
import com.memopet.memopet.domain.pet.dto.MemoryResponseDto;
import com.memopet.memopet.domain.pet.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemoryController {

    private final MemoryService memoryService;

    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/memory")
    public MemoryResponseDto memory(@RequestBody MemoryRequestDto memoryRequestDto) {
        MemoryResponseDto memoryResponseDto = memoryService.findMemoryByMemoryId(Long.valueOf(memoryRequestDto.getMemoryId()));
        return memoryResponseDto;
    }
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/liked-memories")
    public LikedMemoryResponseDto likedMemories(@RequestBody LikedMemoryRequestDto likedMemoryRequestDto) {
        LikedMemoryResponseDto likedMemoryResponseDto = memoryService.findLikedMemoriesByPetId(likedMemoryRequestDto);
        return likedMemoryResponseDto;
    }

//    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
//    @DeleteMapping("/memory")
//    public CommentDeleteResponseDto memory(@RequestBody CommentDeleteRequestDto memoryDeleteRequestDto) {
//
//
//        return commentDeleteResponseDto;
//    }
}
