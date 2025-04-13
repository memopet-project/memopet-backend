package com.memopet.memopet.domain.pet.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.memopet.memopet.domain.pet.entity.QMemoryImage.memoryImage;

@Repository
public class CustomMemoryImageRepositoryImpl implements CustomMemoryImageRepository{

    private final JPAQueryFactory queryFactory;

    public CustomMemoryImageRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public void deleteAllMemoryImages(List<Long> memoryIds) {

        queryFactory.update(memoryImage)
                .set(memoryImage.deletedDate, LocalDateTime.now())
                .where(memoryImage.memory.id.in(memoryIds))
                .where(memoryImage.deletedDate.isNull())
                .execute();
    }

}
