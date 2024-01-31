package com.memopet.memopet.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Meta extends LastModifiedEntity {

    @Id @GeneratedValue
    @Column(name = "meta_data_id")
    private Long id;

    @Column(name = "table_name",nullable = false)
    private String tableName;

    @Column(name = "column_name", nullable = false)
    private String column_name;

    @Column(name = "meta_data", nullable = false)
    private int metaData;

    @Column(name = "meta_data_name", nullable = false)
    private String metaDataName;


}
