package com.memopet.memopet.global.common.entity;

import com.memopet.memopet.domain.pet.entity.Pet;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class RecentSearch {
    @Id @GeneratedValue
    @Column(name = "recent_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet petId;

    @Column(name = "search_text")
    private String searchText;

    @CreatedDate @Column(name = "created_date")
    private LocalDateTime createdDate;
}
