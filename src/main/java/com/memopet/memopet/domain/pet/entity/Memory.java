package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Memory extends FirstCreatedEntity {
    @Id @GeneratedValue
    @Column(name = "memory_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petId;

    @Column(name = "memory_title",nullable = false)
    private String title;

    @Column(name = "memory_date",nullable = false)
    private LocalDateTime memoryDate;

    @Column(name = "memory_desc")
    private String description;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Audience audience;




//    @ManyToOne
//    @JoinColumn(name = "modified_id")
//    private Member modifier;



}
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Comment> comments;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<MemoryImage> memoryImages;

