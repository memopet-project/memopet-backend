package com.memopet.memopet.domain.member.entity;

import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends LastModifiedEntity {
    @Id @GeneratedValue
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    @Column(name="created_data")
    private LocalDateTime createdDate;

}
