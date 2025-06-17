package com.memopet.memopet.domain.member.controller;

import com.memopet.memopet.domain.member.dto.*;
import com.memopet.memopet.domain.member.service.MemberService;
import com.memopet.memopet.global.common.dto.RestResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class MemberController {
    private final MemberService memberService;

    // update member's info
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @PatchMapping("/member-info")
    public RestResult changeMemberInfo(@RequestBody @Valid MemberInfoUpdateRequestDto memberInfoUpdateRequestDto) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.changeMemberInfo(memberInfoUpdateRequestDto);

        return new RestResult(Map.of("changeMemberInfoResponse", memberInfoResponseDto));
    }

    // retrieve member's info
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/member-profile")
    public RestResult retrieveMemberProfile(Authentication authentication) {
        MemberProfileResponseDto memberProfileResponseDto = memberService.getMemberProfile(authentication.getName());

        return new RestResult(Map.of("retrieveMemberProfileResponse", memberProfileResponseDto));
    }

    // deactivate member
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @DeleteMapping("/user")
    public RestResult deactivateMember(@RequestBody DeactivateMemberRequestDto deactivateMemberRequestDto) {
        DeactivateMemberResponseDto deactivateMemberResponseDto = memberService.deactivateMember(deactivateMemberRequestDto.getEmail(), deactivateMemberRequestDto.getDeactivationReason(), deactivateMemberRequestDto.getDeactivationReasonComment());

        return new RestResult(Map.of("deactivateMemberResponse", deactivateMemberResponseDto));
    }
}
