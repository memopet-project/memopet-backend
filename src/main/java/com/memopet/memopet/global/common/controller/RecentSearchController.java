package com.memopet.memopet.global.common.controller;

import com.memopet.memopet.global.common.dto.SearchResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recent_search")
@Validated
public class RecentSearchController {
//    @GetMapping("/{searchText}/memories_profiles")
//    public SearchResponseDTO SearchMemoriesAndProfiles(@PathVariable String searchText) {
//
//    }


}
