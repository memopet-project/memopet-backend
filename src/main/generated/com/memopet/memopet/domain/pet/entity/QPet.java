package com.memopet.memopet.domain.pet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPet is a Querydsl query type for Pet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPet extends EntityPathBase<Pet> {

    private static final long serialVersionUID = 1550498382L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPet pet = new QPet("pet");

    public final com.memopet.memopet.global.common.entity.QFirstCreatedEntity _super = new com.memopet.memopet.global.common.entity.QFirstCreatedEntity(this);

    public final StringPath backImgUrl = createString("backImgUrl");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> deletedDate = createDateTime("deletedDate", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final BooleanPath hideStatus = createBoolean("hideStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final com.memopet.memopet.domain.member.entity.QMember member;

    public final DatePath<java.time.LocalDate> PetBirth = createDate("PetBirth", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> PetDeathDate = createDate("PetDeathDate", java.time.LocalDate.class);

    public final StringPath petFavs = createString("petFavs");

    public final StringPath petFavs2 = createString("petFavs2");

    public final StringPath petFavs3 = createString("petFavs3");

    public final StringPath PetName = createString("PetName");

    public final StringPath petProfileUrl = createString("petProfileUrl");

    public final QSpecies species;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QPet(String variable) {
        this(Pet.class, forVariable(variable), INITS);
    }

    public QPet(Path<? extends Pet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPet(PathMetadata metadata, PathInits inits) {
        this(Pet.class, metadata, inits);
    }

    public QPet(Class<? extends Pet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.memopet.memopet.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.species = inits.isInitialized("species") ? new QSpecies(forProperty("species")) : null;
    }

}

