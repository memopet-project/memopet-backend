package com.memopet.memopet.global.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 547020587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment1 = new QComment("comment1");

    public final QFirstCreatedEntity _super = new QFirstCreatedEntity(this);

    public final StringPath comment = createString("comment");

    public final com.memopet.memopet.domain.member.entity.QMember commenterId;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> deleteDate = createDateTime("deleteDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final com.memopet.memopet.domain.pet.entity.QMemory memory;

    public final NumberPath<Long> parentCommentId = createNumber("parentCommentId", Long.class);

    public final com.memopet.memopet.domain.pet.entity.QPet pet;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commenterId = inits.isInitialized("commenterId") ? new com.memopet.memopet.domain.member.entity.QMember(forProperty("commenterId"), inits.get("commenterId")) : null;
        this.memory = inits.isInitialized("memory") ? new com.memopet.memopet.domain.pet.entity.QMemory(forProperty("memory"), inits.get("memory")) : null;
        this.pet = inits.isInitialized("pet") ? new com.memopet.memopet.domain.pet.entity.QPet(forProperty("pet"), inits.get("pet")) : null;
    }

}

