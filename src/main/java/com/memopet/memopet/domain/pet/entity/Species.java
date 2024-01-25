package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Species extends LastModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_species_id")
    private Long id;

    @Column(name = "species_large_category")
    private String largeCategory;

    @Column(name = "species_mid_category")
    private String midCategory;

    @Column(name = "species_small_category")
    private String smallCategory;
}
