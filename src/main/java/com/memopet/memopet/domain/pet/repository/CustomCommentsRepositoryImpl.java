package com.memopet.memopet.domain.pet.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.memopet.memopet.domain.pet.entity.QComment.comment1;


@Repository
public class CustomCommentsRepositoryImpl implements CustomCommentsRepository{
    private final JPAQueryFactory queryFactory;

    public CustomCommentsRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    @Transactional
    public void deleteAllComments(List<Long> petIds) {
        queryFactory.update(comment1)
                .set(comment1.deletedDate, LocalDateTime.now())
                .where(comment1.pet.id.in(petIds))
                .where(comment1.deletedDate.isNull())
                .execute();
    }
}
