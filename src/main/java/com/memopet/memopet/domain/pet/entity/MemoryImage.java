package com.memopet.memopet.domain.pet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MemoryImage {

    @Id @GeneratedValue
    @Column(name = "memory_image_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id",nullable = false)
    private Memory memory;

    @Column(name = "image_url",nullable = false)
    private String url;

    @Column(name = "image_format",nullable = false)
    private String imageFormat;

    @Column(name = "image_size",nullable = false)
    private String imageSize;

    @Column(name = "image_physical_name",nullable = false)
    private String imagePhysicalName;

    @Column(name = "image_logical_name",nullable = false)
    private String imageLogicalName;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    // 삭제한 사람 Id 필요?
}
