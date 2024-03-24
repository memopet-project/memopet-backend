package com.memopet.memopet.domain.pet.service;

import com.memopet.memopet.domain.pet.dto.LikedMemoryRequestDto;
import com.memopet.memopet.domain.pet.dto.LikedMemoryResponseDto;
import com.memopet.memopet.domain.pet.dto.MemoryPostRequestDto;
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
import org.springframework.stereotype.Service;
import com.memopet.memopet.domain.pet.entity.MemoryImage;
import com.memopet.memopet.domain.pet.repository.MemoryImageRepository;
import com.memopet.memopet.global.common.service.S3Uploader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final LikesRepository likesRepository;
    private final PetRepository petRepository;
    private final MemoryImageRepository memoryImageRepository;
    private final S3Uploader s3Uploader;

    public MemoryResponseDto findMemoryByMemoryId(Long memoryId) {
        Optional<Memory> memory1 = memoryRepository.findById(memoryId);
        Memory memory;
        MemoryResponseDto memoryResponseDto;
        if (memory1.isPresent()) {
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

    /**
     * 추억 생성
     */
    public boolean postMemoryAndMemoryImages(List<MultipartFile> files, MemoryPostRequestDto memoryPostRequestDTO) {
        Memory memory = createAMemory(memoryPostRequestDTO);
        if (memory == null) {
            return false;
        }

        if (!createMemoryImages(memory, files)) {
            memoryRepository.deleteById(memory.getId());
            return false;
        }
        return true;
    }


    /**
     * (추억 생성)-추억 글
     */
    public Memory createAMemory(MemoryPostRequestDto memoryPostRequestDTO) {
        Pet pet = petRepository.findById(memoryPostRequestDTO.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found with id: " + memoryPostRequestDTO.getPetId()));
        Memory memory = Memory.builder()
                .pet(pet)
                .title(memoryPostRequestDTO.getMemoryTitle())
                .memoryDate(memoryPostRequestDTO.getMemoryDate())
                .memoryDescription(memoryPostRequestDTO.getMemoryDesc())
                .audience(memoryPostRequestDTO.getAudience())
                .build();
        return memoryRepository.save(memory);
    }

    /**
     * (추억 생성)-사진
     */
    public boolean createMemoryImages(Memory memory, List<MultipartFile> files) {
        List<MemoryImage> images = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String storedMemoryImgUrl = getS3Url(file);
                MemoryImage memoryImage = getMemoryImage(memory, file, storedMemoryImgUrl);

                images.add(memoryImage);
            }

            if (images.size() == files.size()) {
                memoryImageRepository.saveAll(images);
                return true;
            } else {
                throw new RuntimeException("Not all memory images were created successfully");
            }

        } catch (Exception e) {

            cleanupAndThrowException(images, e);
            return false; // Return false to indicate that the operation failed
        }
    }


    /**
     * (추억 생성)-중간에 오류 나면 생성 중이던 사진을 지운다 (db와 s3)
     */
    private void cleanupAndThrowException(List<MemoryImage> images, Exception originalException) {
        // If there was an issue saving images, delete uploaded files from S3
        for (MemoryImage image : images) {
            try {
                s3Uploader.deleteS3(image.getUrl());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // Remove any associated records from the database
        memoryImageRepository.deleteAll(images);

        // Throw original exception with additional context
        throw new RuntimeException("Error occurred while creating memory images", originalException);
    }

    /**
     * (추억 생성)-사진을 DB에 저장 한다.
     */
    private static MemoryImage getMemoryImage(Memory memory, MultipartFile file, String storedMemoryImgUrl) {
        try {
            return MemoryImage.builder()
                    .url(storedMemoryImgUrl)
                    .imageFormat(file.getContentType())
                    .memory(memory)
                    .imageSize(String.valueOf(file.getSize()))
                    .imageLogicalName(UUID.randomUUID().toString())
                    .imagePhysicalName(file.getOriginalFilename())
                    .build();
        } catch (Exception exp) {
            throw new RuntimeException("Error creating MemoryImage Builder", exp);

        }
    }

    private String getS3Url(MultipartFile file) {
        try {
            System.out.println("MemoryImage s3Uploader upload start");
            return s3Uploader.uploadFileToS3(file, "static/Memory-image");
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to S3", e);
        }
    }
}