package com.memopet.memopet.domain.pet.service;


import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.entity.*;
import com.memopet.memopet.domain.pet.repository.LikesRepository;
import com.memopet.memopet.domain.pet.repository.MemoryImageRepository;
import com.memopet.memopet.domain.pet.repository.MemoryRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final MemoryImageRepository memoryImageRepository;
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
        // 추억 이미지 가져오기
        List<MemoryImage> memoryImages = memoryImageRepository.findByMemoryId(memory.getId());
        Queue<MemoryImage> q = new LinkedList<>();

        memoryImages.forEach(memoryImage -> {
            q.offer(memoryImage);
        });

        memoryResponseDto = MemoryResponseDto.builder()
                            .memoryImageUrlId1(!q.isEmpty() ? q.peek().getId() : null)
                            .memoryImageUrl1(!q.isEmpty() ? q.poll().getUrl() : null)
                            .memoryImageUrlId2(!q.isEmpty() ? q.peek().getId() : null)
                            .memoryImageUrl2(!q.isEmpty() ? q.poll().getUrl() : null)
                            .memoryImageUrlId3(!q.isEmpty() ? q.peek().getId() : null)
                            .memoryImageUrl3(!q.isEmpty() ? q.poll().getUrl() : null)
                            .memoryId(memory.getId())
                            .memoryTitle(memory.getTitle())
                            .memoryDescription(memory.getMemoryDescription())
                            .memoryDate(memory.getMemoryDate())
                            .build();

        return memoryResponseDto;
    }


    public LikedMemoryResponseDto findLikedMemoriesByPetId(LikedMemoryRequestDto likedMemoryRequestDto) {
        List<Long> memoryIds = new ArrayList<>();
        List<MemoryResponseDto> memoriesContent = new ArrayList<>();

        Optional<Pet> pet = petRepository.findById(likedMemoryRequestDto.getPetId());

        // get likes by pet id
        PageRequest pageRequest = PageRequest.of(likedMemoryRequestDto.getCurrentPage()-1, likedMemoryRequestDto.getDataCounts());
        if(!pet.isPresent()) {
            return LikedMemoryResponseDto.builder().totalPages(0).currentPage(0).dataCounts(0).memoryResponseDto(memoriesContent).build();
        }

        Page<Likes> page = likesRepository.findLikesByPetId(pet.get().getId(), pageRequest);
        Queue<MemoryImage> q = new LinkedList<>();
        List<MemoryImage> memoryImages = null;

        List<Likes> likesLs = page.getContent();
        if(likesLs != null) {
            for (Likes like : likesLs) {
                memoryIds.add(like.getMemoryId().getId());
            }

            List<Memory> memories = memoryRepository.findByMemoryIds(memoryIds);

            for (Memory m : memories) {
                memoryImages = memoryImageRepository.findByMemoryId(m.getId());
                memoryImages.forEach(memoryImage -> {
                    q.offer(memoryImage);
                });

                memoriesContent.add(MemoryResponseDto.builder()
                        .memoryId(m.getId())
                        .memoryTitle(m.getTitle())
                        .memoryDate(m.getMemoryDate())
                        .memoryImageUrlId1(!q.isEmpty() ? q.peek().getId() : null)
                        .memoryImageUrl1(!q.isEmpty() ? q.poll().getUrl() : null)
                        .memoryImageUrlId2(!q.isEmpty() ? q.peek().getId() : null)
                        .memoryImageUrl2(!q.isEmpty() ? q.poll().getUrl() : null)
                        .memoryImageUrlId3(!q.isEmpty() ? q.peek().getId() : null)
                        .memoryImageUrl3(!q.isEmpty() ? q.poll().getUrl() : null)
                        .build());
            }
        }

        LikedMemoryResponseDto likedMemoryResponseDto = LikedMemoryResponseDto.builder().totalPages(page.getTotalPages()).currentPage(page.getNumber()+1).dataCounts(page.getContent().size()).memoryResponseDto(memoriesContent).build();
        return likedMemoryResponseDto;
    }

    public LikedMemoryResponseDto findMainMemoriesByPetId(LikedMemoryRequestDto likedMemoryRequestDto) {

        Optional<Pet> pet = petRepository.findById(Long.valueOf(likedMemoryRequestDto.getPetId()));

        List<MemoryResponseDto> memoriesContent = new ArrayList<>();
        List<Long> memoryIds = new ArrayList<>();
        // get likes by pet id
        PageRequest pageRequest = PageRequest.of(likedMemoryRequestDto.getCurrentPage()-1, likedMemoryRequestDto.getDataCounts());
        if(!pet.isPresent()) {
            return LikedMemoryResponseDto.builder().totalPages(0).currentPage(0).dataCounts(0).memoryResponseDto(memoriesContent).build();
        }

        Page<Likes> page = likesRepository.findLikesByPetId(pet.get().getId(), pageRequest);
        List<Likes> likesLs = page.getContent();

        if(likesLs != null) {
            for (Likes like : likesLs) {
                memoryIds.add(like.getMemoryId().getId());
            }

            List<Memory> memories = memoryRepository.findByRecentMemoryIds(memoryIds, LocalDateTime.now().minusDays(7));
            Queue<MemoryImage> q = new LinkedList<>();
            List<MemoryImage> memoryImages = null;
            for (Memory m : memories) {

                memoryImages = memoryImageRepository.findByMemoryId(m.getId());
                memoryImages.forEach(memoryImage -> {
                    q.offer(memoryImage);
                });

                memoriesContent.add(MemoryResponseDto.builder()
                        .memoryId(m.getId())
                        .memoryTitle(m.getTitle())
                        .memoryDate(m.getMemoryDate())
                        .memoryImageUrlId1(!q.isEmpty() ? q.peek().getId() : null)
                        .memoryImageUrl1(!q.isEmpty() ? q.poll().getUrl() : null)
                        .memoryImageUrlId2(!q.isEmpty() ? q.peek().getId() : null)
                        .memoryImageUrl2(!q.isEmpty() ? q.poll().getUrl() : null)
                        .memoryImageUrlId3(!q.isEmpty() ? q.peek().getId() : null)
                        .memoryImageUrl3(!q.isEmpty() ? q.poll().getUrl() : null)
                        .build());
            }
        }

        LikedMemoryResponseDto likedMemoryResponseDto = LikedMemoryResponseDto.builder().totalPages(page.getTotalPages()).currentPage(page.getNumber()+1).dataCounts(page.getContent().size()).memoryResponseDto(memoriesContent).build();
        return likedMemoryResponseDto;

    }

    public MonthMemoriesResponseDto findMonthMemoriesByPetId(MonthMemoriesRequestDto monthMemoriesRequestDto) {
        MonthMemoriesResponseDto monthMemoriesResponseDto;
        // pet id로 펫 정보를 가져옴
        Optional<Pet> pet = petRepository.findById(Long.valueOf(monthMemoriesRequestDto.getPetId()));
        if(!pet.isPresent()) return monthMemoriesResponseDto = MonthMemoriesResponseDto.builder().build();

        Pet petInfo = pet.get();

        // 페이지 네이션용 pagable 객체 생성;
        PageRequest pageRequest = PageRequest.of(monthMemoriesRequestDto.getCurrentPage()-1, monthMemoriesRequestDto.getDataCounts());

        // yearMonth로 null 이면 최신 추억을 찾아 해당 달의 정보를 가져옴
        String yearMonth = monthMemoriesRequestDto.getYearMonth();
        Page<Memory> page;

        if(yearMonth == null) {
            Optional<Memory> theRecentMomory = memoryRepository.findTheRecentMomoryByPetId(petInfo.getId());

            if(theRecentMomory.get() == null) return monthMemoriesResponseDto = MonthMemoriesResponseDto.builder().build();

            Memory memory = theRecentMomory.get();
            LocalDateTime recentPostedDate = memory.getCreatedDate();

            LocalDateTime firstDayOfMonth =beginningOfMonth(recentPostedDate);
            LocalDateTime lastDayOfMonth =endOfMonth(recentPostedDate);
            page = memoryRepository.findMonthMomoriesByPetId(petInfo.getId(), firstDayOfMonth, lastDayOfMonth, pageRequest);

        } else {
            // yearmonth로 받으면 해당 달 20개 를 가져옴
            int year = Integer.parseInt(yearMonth.substring(0,4));
            int month = Integer.parseInt(yearMonth.substring(4,6));
            LocalDateTime localDateTime = LocalDateTime.of(year, month, 01, 00, 00, 00);

            LocalDateTime firstDayOfMonth =beginningOfMonth(localDateTime);
            LocalDateTime lastDayOfMonth =endOfMonth(localDateTime);
            page = memoryRepository.findMonthMomoriesByPetId(petInfo.getId(),firstDayOfMonth,lastDayOfMonth, pageRequest);
        }
        List<Memory> memories = page.getContent();

        List<MemoryResponseDto> memoryResponseDtos = new ArrayList<>();
        Queue<MemoryImage> q = new LinkedList<>();
        List<MemoryImage> memoryImages = null;
        for(Memory memory : memories) {

            memoryImages = memoryImageRepository.findByMemoryId(memory.getId());
            memoryImages.forEach(memoryImage -> {
                q.offer(memoryImage);
            });

            memoryResponseDtos.add(MemoryResponseDto.builder()
                    .memoryImageUrlId1(!q.isEmpty() ? q.peek().getId() : null)
                    .memoryImageUrl1(!q.isEmpty() ? q.poll().getUrl() : null)
                    .memoryImageUrlId2(!q.isEmpty() ? q.peek().getId() : null)
                    .memoryImageUrl2(!q.isEmpty() ? q.poll().getUrl() : null)
                    .memoryImageUrlId3(!q.isEmpty() ? q.peek().getId() : null)
                    .memoryImageUrl3(!q.isEmpty() ? q.poll().getUrl() : null)
                    .memoryDate(memory.getMemoryDate())
                    .memoryTitle(memory.getTitle())
                    .memoryId(memory.getId())
                    .memoryDescription(memory.getMemoryDescription())
                    .build());
        }

        monthMemoriesResponseDto = MonthMemoriesResponseDto.builder().currentPage(page.getNumber()+1).totalPages(page.getTotalPages()).dataCounts(page.getContent().size()).memoryResponseDto(memoryResponseDtos).build();
        return monthMemoriesResponseDto;
    }

    @Transactional(readOnly = false)
    public MemoryDeleteResponseDto deleteMemory(MemoryDeleteRequestDto memoryDeleteRequestDto) {
        Long memoryId = memoryDeleteRequestDto.getMemoryId();
        Optional<Memory> memoryOptional = memoryRepository.findById(memoryId);
        MemoryDeleteResponseDto memoryDeleteResponseDto;
        if(!memoryOptional.isPresent()) return memoryDeleteResponseDto = MemoryDeleteResponseDto.builder().decCode('0').errorMsg("해당 memory id로 조회되는 데이터가 없습니다.").build();
        Memory memory = memoryOptional.get();
        memory.updateDeleteDate(LocalDateTime.now());

        memoryDeleteResponseDto = MemoryDeleteResponseDto.builder().decCode('1').build();
        return memoryDeleteResponseDto;
    }

    @Transactional(readOnly = false)
    public MemoryUpdateResponseDto updateMemoryInfo(MemoryUpdateRequestDto memoryUpdateRequestDto, List<MultipartFile> files) {

        Optional<Memory> memoryOptional = memoryRepository.findById(memoryUpdateRequestDto.getMemoryId());
        if(!memoryOptional.isPresent()) return MemoryUpdateResponseDto.builder().decCode('0').errorMsg("해당 추억 ID로 조회된 데이터가 없습니다.").build();
        Memory memory = memoryOptional.get();

        //추억제목
        if(memoryUpdateRequestDto.getMemoryTitle() != null || memoryUpdateRequestDto.getMemoryTitle().equals("")) {
            memory.updateTitle(memoryUpdateRequestDto.getMemoryTitle());
        }

        //추억날짜
        if(memoryUpdateRequestDto.getMemoryDate() != null || memoryUpdateRequestDto.getMemoryDate().equals("")) {
            memory.updateMemoryDate(memoryUpdateRequestDto.getMemoryDate());
        }

        //추억 설명
        if(memoryUpdateRequestDto.getMemoryDescription() != null || memoryUpdateRequestDto.getMemoryDescription().equals("")) {
            memory.updateDesc(memoryUpdateRequestDto.getMemoryDescription());
        }

        //공개범위
        if(memoryUpdateRequestDto.getOpenRestrictionLevel() != null || memoryUpdateRequestDto.getOpenRestrictionLevel().equals("")) {
            Audience audience = null;
            if(memoryUpdateRequestDto.getOpenRestrictionLevel() == 1)  audience = Audience.ALL;
            if(memoryUpdateRequestDto.getOpenRestrictionLevel() == 2)  audience = Audience.FRIEND;
            if(memoryUpdateRequestDto.getOpenRestrictionLevel() == 3)  audience = Audience.ME;
            memory.updateAudience(audience);
        }

        return MemoryUpdateResponseDto.builder().decCode('1').errorMsg("수정 완료됬습니다.").build();
    }

    /**
     * beginningOfMonth
     * <pre>
     * 파라미터로 받은 날짜에 해당되는 달의 첫번째 날짜를 리턴한다.
     * </pre>
     * @param dateTime
     * @return
     */
    public static LocalDateTime beginningOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0);
    }

    /**
     * endOfMonth
     * <pre>
     * 파라미터로 받은 날짜에 해당되는 달의 마지막 날짜(23시 59분 99초)를 리턴한다.
     * </pre>
     * @param dateTime
     * @return
     */
    public static LocalDateTime endOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), daysInMonth(dateTime), 23, 59, 59, 999_999_999);
    }
    /**
     * daysInMonth
     * <pre>
     * 파라미터로 받은 날짜에 해당되는 달의 총 일수를 리턴한다.
     * </pre>
     * @param dateTime
     * @return
     */
    public static int daysInMonth(LocalDateTime dateTime) {
        return dateTime.getMonth().length(dateTime.toLocalDate().isLeapYear()); //isLeapYear은 윤년여부
    }

}

