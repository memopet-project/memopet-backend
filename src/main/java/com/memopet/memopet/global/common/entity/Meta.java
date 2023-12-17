package com.memopet.memopet.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Meta extends FirstCreatedEntity {

    @Id
    @Column(name = "table_name")
    private String tableName;

    @Id
    @Column(name = "column_value")
    private int columnValue;

    @Column(name = "column_name")
    private String column_name;
}
