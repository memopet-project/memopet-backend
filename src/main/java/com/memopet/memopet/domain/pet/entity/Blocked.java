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
    @JoinColumn(name = "blocker_pet", referencedColumnName = "pet_id",nullable = false)
    private Pet blockerPet;

    @Column(name = "blocked_pet", nullable = false)
    private Long blockedPet;

    @CreatedDate @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

}
