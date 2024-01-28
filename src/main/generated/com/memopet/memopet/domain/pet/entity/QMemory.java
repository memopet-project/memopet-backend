package com.memopet.memopet.domain.pet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemory is a Querydsl query type for Memory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemory extends EntityPathBase<Memory> {

    private static final long serialVersionUID = -1561955982L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemory memory = new QMemory("memory");

    public final com.memopet.memopet.global.common.entity.QFirstCreatedEntity _super = new com.memopet.memopet.global.common.entity.QFirstCreatedEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> deletedDate = createDateTime("deletedDate", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> memoryDate = createDateTime("memoryDate", java.time.LocalDateTime.class);

    public final com.memopet.memopet.domain.member.entity.QMember modifier;

    public final StringPath title = createString("title");

    public QMemory(String variable) {
        this(Memory.class, forVariable(variable), INITS);
    }

    public QMemory(Path<? extends Memory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemory(PathMetadata metadata, PathInits inits) {
        this(Memory.class, metadata, inits);
    }

    public QMemory(Class<? extends Memory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.modifier = inits.isInitialized("modifier") ? new com.memopet.memopet.domain.member.entity.QMember(forProperty("modifier"), inits.get("modifier")) : null;
    }

}

