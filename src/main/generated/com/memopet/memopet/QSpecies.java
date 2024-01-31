package com.memopet.memopet;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpecies is a Querydsl query type for Species
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpecies extends EntityPathBase<Species> {

    private static final long serialVersionUID = 681645597L;

    public static final QSpecies species = new QSpecies("species");

    public final QLastModifiedEntity _super = new QLastModifiedEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> largeCategory = createNumber("largeCategory", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final NumberPath<Long> midCategory = createNumber("midCategory", Long.class);

    public final NumberPath<Long> smallCategory = createNumber("smallCategory", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QSpecies(String variable) {
        super(Species.class, forVariable(variable));
    }

    public QSpecies(Path<? extends Species> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpecies(PathMetadata metadata) {
        super(Species.class, metadata);
    }

}

