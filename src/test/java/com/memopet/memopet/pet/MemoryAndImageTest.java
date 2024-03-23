package com.memopet.memopet.pet;

import com.memopet.memopet.domain.member.controller.AuthController;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.controller.MemoryController;
import com.memopet.memopet.domain.pet.dto.MemoryPostRequestDto;
import com.memopet.memopet.domain.pet.entity.*;
import com.memopet.memopet.domain.pet.repository.MemoryImageRepository;
import com.memopet.memopet.domain.pet.repository.MemoryRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
import com.memopet.memopet.domain.pet.service.MemoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class MemoryAndImageTest {
    @Autowired
    MemoryService memoryService;

    @Autowired
    MemoryRepository memoryRepository;

    @Autowired
    MemoryController memoryController;

    @Autowired
    MemoryImageRepository memoryImageRepository;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    AuthController authController;


@Test
public void PostAMemoryOnly() throws Exception {
    MemoryPostRequestDto memoryPostRequestDTO = new MemoryPostRequestDto(1L, "삼일절!! 집콕여행", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
    Memory MemoryId=memoryService.createAMemory(memoryPostRequestDTO);
    System.out.println("MemoryId = " + MemoryId);
    MemoryPostRequestDto memoryPostRequestDto2 = new MemoryPostRequestDto(1L, "비오는 날", LocalDateTime.of(2024,2,20,6,4), "추워서 뼈가 시림", Audience.ALL);
    Memory MemoryId2=memoryService.createAMemory(memoryPostRequestDto2);
    System.out.println("MemoryId = " + MemoryId2);
    MemoryPostRequestDto memoryPostRequestDto3 = new MemoryPostRequestDto(1L, "카사독 마지막날", LocalDateTime.of(2024,02,29,12,0), "사요나라 휴먼뜨 :(", Audience.ME);
    Memory MemoryId3=memoryService.createAMemory(memoryPostRequestDto3);
    System.out.println("MemoryId = " + MemoryId3);
    Long petId = 1L;
    Pageable pageable = Pageable.ofSize(20);
    Page<Memory> memories = memoryRepository.findMemoriesByPetId(petId, pageable);
    Assertions.assertThat(memories.getTotalElements()).isEqualTo(3);
    MemoryPostRequestDto memoryPostRequestDto4 = new MemoryPostRequestDto(2L, "카사독 마지막날", LocalDateTime.of(2024,02,29,12,0), "사요나라 휴먼뜨 :(", Audience.ME);
    Memory MemoryId4=memoryService.createAMemory(memoryPostRequestDto4);
    System.out.println("MemoryId = " + MemoryId4);

    Assertions.assertThat(memoryRepository.findAll().size()).isEqualTo(4);

}

@Test
public void memoryAndImages() throws Exception {

    MemoryPostRequestDto memoryPostRequestDTO = new MemoryPostRequestDto(1L, "삼일절!! 집콕여행", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);

    List<MultipartFile> files = Arrays.asList(
            new MockMultipartFile("file1", "meeeeep.jpg", "image/jpeg", "some image".getBytes()),
            new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", "another image".getBytes())
    );
    boolean result= memoryService.postMemoryAndMemoryImages(files, memoryPostRequestDTO);
    Assertions.assertThat(result).isTrue();

    MemoryPostRequestDto memoryPostRequestDto1 = new MemoryPostRequestDto(2L, "삼일절!! 집콕여행", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);

    List<MultipartFile> files2 = Arrays.asList(
            new MockMultipartFile("file1", "meeeeep.jpg", "image/jpeg", "some image".getBytes()),
            new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", "another image".getBytes())
    );
    boolean result2= memoryService.postMemoryAndMemoryImages(files2, memoryPostRequestDto1);
    Assertions.assertThat(result2).isTrue();
    Assertions.assertThat(memoryRepository.findAll().size()).isEqualTo(2);
    Assertions.assertThat(memoryImageRepository.findAll().size()).isEqualTo(4);


    }
//    @Test
//    public void MoreThan10Files() throws Exception {
//        //given
//        MemoryRequestDTO memoryRequestDTO = new MemoryRequestDTO(1L, "삼일절!! 집콕여행", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
//
//        List<MultipartFile> files = Arrays.asList(
//                new MockMultipartFile("file1", "meeeeep.jpg", "image/jpeg", "some image".getBytes()),
//                new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file3", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file4", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file5", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file6", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file7", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file8", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file9", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file10", "anotherimage.jpg", "image/jpeg", "another image".getBytes()),
//                new MockMultipartFile("file11", "anotherimage.jpg", "image/jpeg", "another image".getBytes())
//        );
//         MemoryResponseDTO result= memoryController.postAMemory(files, memoryRequestDTO);
//        Assertions.assertThat(result.getDecCode()).isEqualTo('0');
//    }
//    @Test
//    public void title60words() throws Exception {
//        //given
//        MemoryRequestDTO memoryRequestDTO = new MemoryRequestDTO(1L, "1123456789012345678901234567890123456789012345678901234567890", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
//        List<MultipartFile> files = Arrays.asList(
//                new MockMultipartFile("file1", "meeeeep.jpg", "image/jpeg", "some image".getBytes()),
//                new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", "another image".getBytes())
//        );
//        ConstraintViolationException exception= org.junit.jupiter.api.Assertions.assertThrows(ConstraintViolationException.class,() ->{
//            memoryController.postAMemory(files, memoryRequestDTO);
//        });
//        System.out.println("exception = " + exception.getMessage());
//
//    }
//    @Test
//    public void ValidFileContentType() throws Exception {
//        //given
//        MemoryRequestDTO memoryRequestDTO = new MemoryRequestDTO(1L, "5678901234567890123456789012345678901234567890", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
//        List<MultipartFile> files = Arrays.asList(
//                new MockMultipartFile("file1", "meeeeep.jpg", "meep", "some image".getBytes()),
//                new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", "another image".getBytes())
//        );
//            MemoryResponseDTO result= memoryController.postAMemory(files, memoryRequestDTO);
//        Assertions.assertThat(result.getDecCode()).isEqualTo('0');
//        Assertions.assertThat(memoryRepository.findAll().size()).isEqualTo(0);
//        Assertions.assertThat(memoryImageRepository.findAll().size()).isEqualTo(0); }
//    @Test
//    public void fileSize10mb() throws Exception {
//        //given
//        byte[] largeFileContent = new byte[20 * 1024 * 1024];
//        MemoryRequestDTO memoryRequestDTO = new MemoryRequestDTO(1L, "5678901234567890123456789012345678901234567890", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
//        List<MultipartFile> files = List.of(
//                new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", largeFileContent)
//        );
//        MemoryResponseDTO result= memoryController.postAMemory(files, memoryRequestDTO);
//        Assertions.assertThat(result.getDecCode()).isEqualTo('0');
//   //given
//        byte[] fileContentSize = new byte[10 * 1024 * 1024];
//        MemoryRequestDTO memoryRequestDTO2 = new MemoryRequestDTO(1L, "5678901234567890123456789012345678901234567890", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
//        List<MultipartFile> files2 = List.of(
//                new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", fileContentSize)
//        );
//        MemoryResponseDTO result2= memoryController.postAMemory(files2, memoryRequestDTO2);
//        Assertions.assertThat(result2.getDecCode()).isEqualTo('1');
//
//    }
    @Test
    public void createMemoryImagesServiceFunction() throws Exception {
        //given
        MemoryPostRequestDto memoryPostRequestDTO = new MemoryPostRequestDto(1L, "1123456789012345678901234567890123456789012345678901234567890", LocalDateTime.now(), "날씨가 매우 추워서 양말을 신어야했다 :( 이것이 바로 꽃샘추위", Audience.FRIEND);
        Memory memory = memoryService.createAMemory(memoryPostRequestDTO);
        List<MultipartFile> files = Arrays.asList(
                new MockMultipartFile("file1", "meeeeep.jpg", "image/jpeg", "some image".getBytes()),
                new MockMultipartFile("file2", "anotherimage.jpg", "image/jpeg", "another image".getBytes())
        );
        //when
        boolean result= memoryService.createMemoryImages(memory, files);
        //then
        Assertions.assertThat(result).isTrue();
    }

}

