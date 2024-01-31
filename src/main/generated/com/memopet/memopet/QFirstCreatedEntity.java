package com.memopet.memopet;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFirstCreatedEntity is a Querydsl query type for FirstCreatedEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QFirstCreatedEntity extends EntityPathBase<FirstCreatedEntity> {

    private static final long serialVersionUID = 2087633050L;

    public static final QFirstCreatedEntity firstCreatedEntity = new QFirstCreatedEntity("firstCreatedEntity");

    public final QLastModifiedEntity _super = new QLastModifiedEntity(this);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QFirstCreatedEntity(String variable) {
        super(FirstCreatedEntity.class, forVariable(variable));
    }

    public QFirstCreatedEntity(Path<? extends FirstCreatedEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFirstCreatedEntity(PathMetadata metadata) {
        super(FirstCreatedEntity.class, metadata);
    }

}

