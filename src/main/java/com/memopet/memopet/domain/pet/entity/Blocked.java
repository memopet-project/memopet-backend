package com.memopet.memopet.domain.pet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Blocked {

    @Id @GeneratedValue
    @Column(name = "Blocked_id")
    private Long id;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_pet", referencedColumnName = "pet_id",nullable = false)
    private Pet blockedPet; //blockerPet이랑 blockedPet 바꿈.

    @Column(name = "blocker_pet_id", nullable = false)
    private Long blockerPetId;


    @CreatedDate @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

}
