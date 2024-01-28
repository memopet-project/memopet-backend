package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends FirstCreatedEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memory;

    //부모 코맨트
    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    //코맨트 단 반려동물(프로필) ID
    @Column(nullable = false)
    private Long commenterId;

    @Column(nullable = false, length = 150)
    private String comment;

    //댓글 깊이, 댓글:0, 대댓글:1
    @Column(name = "depth",nullable = false)
    private int depth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentGroup commentGroup;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;


//    댓글,댓글들 순서 이건 첫 생성일자랑 parent_comment_id로 오더하면 될듯하다.
//    @Column(name = "count")
//    private Long count;


}
