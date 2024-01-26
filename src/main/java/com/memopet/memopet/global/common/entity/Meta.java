package com.memopet.memopet.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Meta extends LastModifiedEntity {

    @Id @GeneratedValue
    @Column(name = "meta_data_id")
    private Long id;
    @Column(name = "table_name")
    private String tableName;

    @Column(name = "column_name")
    private String column_name;

    @Column(name = "meta_data")
    private int metaData;

    @Column(name = "meta_data_name")
    private String metaDataName;


}
