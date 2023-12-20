package com.memopet.memopet.global.common.entity;

import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.entity.Species;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends FirstCreatedEntity{

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    //코맨트 단 사람의 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member commenterId;

    //부모 코맨트
    @Column(name = "parent_comment_id")
    private Long parentCommentId;


    //댓글 깊이, 댓글:0, 대댓글:1
    @Column(name = "depth")
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column (nullable = false,length = 150)
    private String comment;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;


//    댓글,댓글들 순서 이건 첫 생성일자랑 parent_comment_id로 오더하면 될듯하다.
//    @Column(name = "count")
//    private Long count;


    public Comment(Member commenterId,Pet pet, String comment) {
        this.commenterId = commenterId;
        this.pet = pet;
        this.comment = comment;
    }

    public Comment(Member commenterId,Memory memory, String comment) {
        this.commenterId = commenterId;
        this.memory = memory;
        this.comment = comment;
    }

}
