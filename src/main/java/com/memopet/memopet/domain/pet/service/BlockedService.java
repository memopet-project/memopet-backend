package com.memopet.memopet.domain.pet.service;

import com.memopet.memopet.domain.pet.dto.BlockDTO;
import com.memopet.memopet.domain.pet.dto.BlockedListDTO;
import com.memopet.memopet.domain.pet.entity.Blocked;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.repository.BlockedRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockedService {

    private final BlockedRepository blockedRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public Page<BlockedListDTO> blockedPetList(Pageable pageable, Long blockerPetId) {
        return blockedRepository.findBlockedPetsByBlockerPetId(blockerPetId, pageable)
                .map(BlockedListDTO::new);
    }


    public void blockApet(BlockDTO blockDTO) {
        if (blockDTO.getBlockedPetId().equals(blockDTO.getBlockerPetId())) {
            throw new IllegalArgumentException("A pet cannot block itself");
        }
        Pet blockedPet = petRepository.findById(blockDTO.getBlockedPetId())
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        // Check if the blocking relationship already exists
        if (blockedRepository.existsByPetIds(blockDTO.getBlockerPetId(), blockDTO.getBlockedPetId())) {
            throw new IllegalStateException("Blocking relationship already exists");
        }
        Blocked blocked = Blocked.builder()
                .blockedPet(blockedPet)
                .blockerPetId(blockDTO.getBlockerPetId())
                .build();
        blockedRepository.save(blocked);

    }


    public void unblockAPet(BlockDTO blockDTO) {

        // Check if the blocking relationship already exists
        if (!blockedRepository.existsByPetIds(blockDTO.getBlockerPetId(), blockDTO.getBlockedPetId())) {
            throw new IllegalStateException("Blocking relationship does not exist");
        }
        // Find the blocked entity based on the provided blocker and blocked pet IDs
        Blocked blockedEntity = blockedRepository.findByBlockerPetIdAndBlockedPet(
                blockDTO.getBlockerPetId(), petRepository.findById(blockDTO.getBlockedPetId())
                        .orElseThrow(() -> new IllegalArgumentException("Blocked Pet not found")));

        blockedRepository.delete(blockedEntity);
    }
}