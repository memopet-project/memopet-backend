package com.memopet.memopet.domain.pet.service;


import com.memopet.memopet.domain.pet.dto.LikedMemoryRequestDto;
import com.memopet.memopet.domain.pet.dto.LikedMemoryResponseDto;
import com.memopet.memopet.domain.pet.dto.MemoryRequestDto;
import com.memopet.memopet.domain.pet.dto.MemoryResponseDto;
import com.memopet.memopet.domain.pet.entity.Likes;
import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.repository.LikesRepository;
import com.memopet.memopet.domain.pet.repository.MemoryRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final LikesRepository likesRepository;
    private final PetRepository petRepository;

    public MemoryResponseDto findMemoryByMemoryId(Long memoryId) {
        Optional<Memory> memory1 = memoryRepository.findById(memoryId);
        Memory memory;
        MemoryResponseDto memoryResponseDto;
        if(memory1.isPresent()) {
            memory = memory1.get();
        } else {
            return memoryResponseDto = MemoryResponseDto.builder().build();
        }

        memoryResponseDto = MemoryResponseDto.builder()
                            .memoryImageUrl1(memory.getMemoryImageUrl1())
                            .memoryImageUrl2(memory.getMemoryImageUrl2())
                            .memoryImageUrl3(memory.getMemoryImageUrl3())
                            .memoryId(memory.getId())
                            .memoryTitle(memory.getTitle())
                            .memoryDescription(memory.getMemoryDescription())
                            .memoryDate(memory.getMemoryDate())
                            .build();
        return memoryResponseDto;
    }


    public LikedMemoryResponseDto findLikedMemoriesByPetId(LikedMemoryRequestDto likedMemoryRequestDto) {


        Optional<Pet> pet = petRepository.findById(Long.valueOf(likedMemoryRequestDto.getPetId()));
        // get likes by pet id
        PageRequest pageRequest = PageRequest.of(likedMemoryRequestDto.getCurrentPage(), likedMemoryRequestDto.getDataCounts());

        Page<Likes> page = likesRepository.findLikesByPetId(pet.get(), pageRequest);

        List<Long> memoryIds = new ArrayList<>();

        List<Likes> likesLs = page.getContent();




        for (Likes like : likesLs) {
            System.out.println(like.getMemoryId().getId());
            memoryIds.add(like.getMemoryId().getId());
        }

        List<Memory> memories = memoryRepository.findByMemoryIds(memoryIds);
        List<MemoryResponseDto> memoriesContent = new ArrayList<>();

        for (Memory m : memories) {
            memoriesContent.add(MemoryResponseDto.builder()
                                .memoryId(m.getId())
                                .memoryTitle(m.getTitle())
                                .memoryDate(m.getMemoryDate())
                                .memoryImageUrl1(m.getMemoryImageUrl1())
                                .build());
        }


        LikedMemoryResponseDto likedMemoryResponseDto = LikedMemoryResponseDto.builder().totalPages(page.getTotalPages()).currentPage(page.getNumber()).dataCounts(page.getContent().size()).memoryResponseDto(memoriesContent).build();
        return likedMemoryResponseDto;
    }
}
