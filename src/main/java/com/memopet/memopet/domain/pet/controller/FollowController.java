package com.memopet.memopet.domain.pet.controller;

import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.repository.FollowRepository;
import com.memopet.memopet.domain.pet.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;
    //@PageableDefault(size = 20,page = 0)
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PostMapping("")
    public FollowResponseDTO FollowAPet(@RequestBody FollowDTO followDTO) {
        return followService.followAPet(followDTO);
    }
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("")
    public FollowListWrapper followList(Pageable pageable, @RequestBody FollowListRequestDto followListRequestDto){
        return followService.followList(pageable,followListRequestDto);
    }
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @DeleteMapping("")
    public FollowResponseDTO unfollow(@RequestBody FollowDTO followDTO) {
        return followService.unfollow(followDTO);
    }
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @DeleteMapping("/follower")
    public FollowResponseDTO deleteFollower(@RequestBody FollowerDTO followerDTO) {
        return followService.deleteFollower(followerDTO);
    }


}
