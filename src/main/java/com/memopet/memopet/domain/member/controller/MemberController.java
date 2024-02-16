package com.memopet.memopet.domain.member.controller;

import com.memopet.memopet.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    //@PreAuthorize("hasAnyRole('SCOPE_USER','SCOPE_ADMIN')")
    @PreAuthorize("hasAuthority('SCOPE_USER_AUTHORITY')")
    @GetMapping("/welcome-message")
    public ResponseEntity<String> UserMemberMessageTest(Authentication authentication) {
        System.out.println("UserMemberMessageTest start");
        return ResponseEntity.ok("Welcome to the JWT Tutorial:"+authentication.getName()+"with scope:"+authentication.getAuthorities());
    }

    //@PreAuthorize("hasRole('SCOPE_ADMIN')")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN_AUTHORITY')")
    @GetMapping("/admin-message")
    public ResponseEntity<String> getAdminData(){
        return ResponseEntity.ok("admin");

    }
}
