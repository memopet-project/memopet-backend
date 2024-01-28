package com.memopet.memopet.domain.member.entity;

import com.memopet.memopet.domain.pet.entity.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id @GeneratedValue
    @Column(name="report_id")
    private Long report_id;
    @Column(name="report_category")
    private String reportCategory;
    @Column(name="report_reason")
    private String reportReason;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name="reporter_pet_id")
    private String reporterPetId;
    @Column(name="reported_pet_id")
    private String reportedPetId;
    @Column(name="comment_id")
    private String commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    //@ManyToOne(fetch = FetchType.LAZY)
    //private Comment comment;
}
