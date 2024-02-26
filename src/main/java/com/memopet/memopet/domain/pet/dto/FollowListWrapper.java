package com.memopet.memopet.domain.pet.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class FollowListWrapper {
    private Page<FollowListResponseDto> followList;
    private String errorCode;
    private String errorDescription;
}
