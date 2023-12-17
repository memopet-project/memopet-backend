package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memory extends FirstCreatedEntity {

    @Id @GeneratedValue
    @Column(name = "memory_id")
    private Long id;

//    @ManyToOne, memory가 연관관계 주인
//    private Pet pet;

    @Column(name = "memory_title")
    private String title;

    @Column(name = "memory_date")
    private LocalDateTime memoryDate;

    @Column(name = "memory_desc")
    private String description;

    @Column(name = "like_count")
    private Long likeCount;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Comment> comments;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<MemoryImage> memoryImages;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "modified_id")
    private Member modifier;

}
