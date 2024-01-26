package com.memopet.memopet.domain.pet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoryImage {

    @Id @GeneratedValue
    @Column(name = "memory_image_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @Column(name = "image_url")
    private String url;

    @Column(name = "image_format")
    private String imageFormat;

    @Column(name = "image_size")
    private Long imageSize;

    @Column(name = "image_physical_name")
    private String imagePhysicalName;

    @Column(name = "image_logical_name")
    private String imageLogicalName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    // 삭제한 사람 Id 필요?
}
