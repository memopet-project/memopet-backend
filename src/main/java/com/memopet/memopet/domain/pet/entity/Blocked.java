package com.memopet.memopet.domain.pet.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Blocked {
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "blocker_pet", referencedColumnName = "pet_id")
    private Pet blockerPet;

    @Column(name = "blocked_pet")
    private Long blockedPet;

    @CreatedDate @Column(name = "created_date")
    private LocalDateTime createdDate;

}
