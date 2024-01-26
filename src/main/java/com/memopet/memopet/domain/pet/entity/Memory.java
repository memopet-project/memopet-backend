package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memory extends LastModifiedEntity {
//meep >:( rawrr!
    @Id @GeneratedValue
    @Column(name = "memory_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet petId;

    @Column(name = "memory_title")
    private String title;

    @Column(name = "memory_date")
    private LocalDateTime memoryDate;

    @Column(name = "memory_desc")
    private String description;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Enumerated(EnumType.STRING)
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