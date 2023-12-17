package com.memopet.memopet.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id @GeneratedValue
    @Column(name="report_id")
    private Long report_id;
    @Column(name="report_category")
    private String reportCategory;
    @Column(name="report_reason")
    private String reportReason;
    @Column(name="reporter_id")
    private String reporterId;
    @Column(name="report_target_id")
    private String reportTargetId;
    @Column(name="comment_id")
    private String commentId;
    @Column(name="pet_species_id")
    private String petSpeciesId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    //@ManyToOne(fetch = FetchType.LAZY)
    //private Comment comment;
}
