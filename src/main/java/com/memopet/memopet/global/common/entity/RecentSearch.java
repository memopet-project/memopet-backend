package com.memopet.memopet.global.common.entity;

import com.memopet.memopet.domain.pet.entity.Pet;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RecentSearch {
    @Id @GeneratedValue
    @Column(name = "recent_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id",nullable = false)
    private Pet pet;

    @Column(name = "search_text", nullable = false)
    private List<String> searchText;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
