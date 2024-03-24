package com.memopet.memopet.domain.pet.dto;

import com.memopet.memopet.domain.pet.entity.Audience;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class MemoryPostRequestDto {
    private Long petId;
    @Size(max = 60, message = "Memory title must be at most 60 characters long")
    private String memoryTitle;
    private LocalDateTime memoryDate;
    @Size(max = 2000, message = "Memory description must be at most 2000 characters long")
    private String memoryDesc;
    private Audience audience;

    public MemoryPostRequestDto(Long petId, String memoryTitle, LocalDateTime memoryDate, String memoryDesc, Audience audience) {
        this.petId = petId;
        this.memoryTitle = memoryTitle;
        this.memoryDate = memoryDate;
        this.memoryDesc = memoryDesc;
        this.audience = audience;
    }
}
