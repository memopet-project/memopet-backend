package com.memopet.memopet.domain.pet.entity;

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
    @Column(name="report_category", nullable = false)
    private String reportCategory;
    @Column(name="report_reason", nullable = false)
    private String reportReason;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name="reporter_pet_id", nullable = false)
    private Long reporterPetId;
    @Column(name="reported_pet_id", nullable = false)
    private Long reportedPetId;
    @Column(name="comment_id")
    private Long commentId;


}
