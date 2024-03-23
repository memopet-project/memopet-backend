package com.memopet.memopet.global.common.service;

import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.global.common.entity.RecentSearch;
import com.memopet.memopet.global.common.repository.RecentSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.memopet.memopet.global.common.entity.QRecentSearch.recentSearch;

@Service
@RequiredArgsConstructor
@Transactional
public class RecentSearchService {
    private final RecentSearchRepository recentSearchRepository;
    private final PetRepository petRepository;

    private static final int MAX_RECENT_SEARCHES = 5;

    public void addRecentSearch(String searchText, Long petId) {
        Pet pet1 = petRepository.getReferenceById(petId);
        if (!recentSearchRepository.existsByPetId(pet1)) {
            RecentSearch recentSearch = RecentSearch.builder()
                    .pet(pet1).build();
        }
        RecentSearch results = recentSearchRepository.findByPet(pet1);
        if (results.getSearchText().size() < MAX_RECENT_SEARCHES) {
            results.getSearchText().add(searchText);
        }
    }
}
