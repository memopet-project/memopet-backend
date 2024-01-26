package com.memopet.memopet.domain.pet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Likes {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memoryID;

    @Column(name = "liked_own_pet_id")
    private Long likedOwnPetId;

    @CreatedDate @Column(name = "created_date")
    private LocalDateTime createdDate;
}
