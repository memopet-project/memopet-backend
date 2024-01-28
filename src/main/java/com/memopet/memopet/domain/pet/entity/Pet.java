package com.memopet.memopet.domain.pet.entity;


import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Pet extends FirstCreatedEntity {

    @Id @GeneratedValue @Column(name = "pet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_species_id")
    private Species species;

    @Column(name = "pet_name")
    private String PetName;

    @Column(name = "pet_birth")
    private LocalDate PetBirth;

    @Column(name = "pet_death_date")
    private LocalDate PetDeathDate;

    @Column(name = "pet_profile_url")
    private String petProfileUrl;

    @Column(name = "back_img_url")
    private String backImgUrl;

    @Column(name = "pet_favs")
    private String petFavs;

    @Column(name = "pet_favs2")
    private String petFavs2;

    @Column(name = "pet_favs3")
    private String petFavs3;

//    @Lob
    @Column(name = "pet_desc")
    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "hide_Status")
    private boolean hideStatus;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    public Pet(Member member, String description) {
        this.member = member;
        this.description = description;
    }

}
