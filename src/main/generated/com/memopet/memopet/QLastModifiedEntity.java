package com.memopet.memopet;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLastModifiedEntity is a Querydsl query type for LastModifiedEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QLastModifiedEntity extends EntityPathBase<LastModifiedEntity> {

    private static final long serialVersionUID = -1307706175L;

    public static final QLastModifiedEntity lastModifiedEntity = new QLastModifiedEntity("lastModifiedEntity");

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public QLastModifiedEntity(String variable) {
        super(LastModifiedEntity.class, forVariable(variable));
    }

    public QLastModifiedEntity(Path<? extends LastModifiedEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLastModifiedEntity(PathMetadata metadata) {
        super(LastModifiedEntity.class, metadata);
    }

}

