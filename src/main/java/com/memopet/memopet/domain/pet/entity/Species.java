package com.memopet.memopet.domain.pet.entity;

import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue
    @Column(name = "pet_species_id")
    private Long id;

    @Column(name = "species_large_category")
    private Long largeCategory;

    @Column(name = "species_mid_category")
    private Long midCategory;

    @Column(name = "species_small_category")
    private Long smallCategory;
}
