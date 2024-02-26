package com.memopet.memopet.domain.pet.service;

import com.memopet.memopet.domain.pet.dto.*;
import com.memopet.memopet.domain.pet.entity.Blocked;
import com.memopet.memopet.domain.pet.entity.Follow;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.repository.FollowRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final PetRepository petRepository;

    public FollowListWrapper followList(Pageable pageable, FollowListRequestDto followListRequestDto) {
        FollowListWrapper wrapper = new FollowListWrapper();

        switch (followListRequestDto.getFollowType()) {
            case 1:
                wrapper.setFollowList(followRepository.findFollowerPetsByPetId(pageable, followListRequestDto.getPetId()));
                break;
            case 2:
                wrapper.setFollowList(followRepository.findFollowingPetsById(pageable, followListRequestDto.getPetId()));
                break;
            default:
                wrapper.setErrorCode("0");
                wrapper.setErrorDescription("Unexpected value: " + followListRequestDto.getFollowType());
                break;
        }

        return wrapper;
    }


    public FollowResponseDTO unfollow(FollowDTO followDTO) {
        if (!followRepository.existsByPetIdAndFollowingPetId(followDTO.getPetId(), followDTO.getFollowingPetId())) {
            return new FollowResponseDTO(0, "Following relation doesn't exist.");
        }
        followRepository.deleteByPetIdAndFollowingPetId(followDTO.getPetId(), followDTO.getFollowingPetId());
        return new FollowResponseDTO(1, "Unfollowed the pet successfully");
    }
    public FollowResponseDTO deleteFollower(FollowerDTO followDerTO) {
        if (!followRepository.existsByPetIdAndFollowingPetId(followDerTO.getPetId(), followDerTO.getFollowingPetId())) {
            return new FollowResponseDTO(0, "Following relation doesn't exist.");
        }
        followRepository.deleteByPetIdAndFollowingPetId(followDerTO.getPetId(), followDerTO.getFollowingPetId());
        return new FollowResponseDTO(1, "Unfollowed the pet successfully");
    }

    public FollowResponseDTO followAPet(FollowDTO followDTO) {
        if (followDTO.getPetId().equals(followDTO.getFollowingPetId())) {
            return new FollowResponseDTO(0, "A pet cannot follow itself");
//            throw new IllegalArgumentException("A pet cannot follow itself");
        }

        Pet followingPet = petRepository.findById(followDTO.getFollowingPetId())
                .orElse(null);
//        Pet followingPet = petRepository.findById(followDTO.getFollowingPetId())
//                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        if (followingPet == null) {
            return new FollowResponseDTO(0, "Pet not found");
        }

        if (followRepository.existsByPetIdAndFollowingPetId(followDTO.getPetId(),followDTO.getFollowingPetId())) {
            return new FollowResponseDTO(0,"Following relationship already exists");
        }

        Follow follow = Follow.builder()
                .petId(followDTO.getPetId())
                .followingPet(followingPet)
                .build();
        followRepository.save(follow);
        return new FollowResponseDTO(1, "Followed the pet successfully");
    }
}
