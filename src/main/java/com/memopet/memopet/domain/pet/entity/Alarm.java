package com.memopet.memopet.domain.pet.entity;


import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alarm extends FirstCreatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "alarm_type", nullable = false)
    private String alarmType;

    @Column(name = "alarm_content")
    private String alarmContent;

    @Column(name = "readYn", nullable = false)
    private String read_yn;


}
