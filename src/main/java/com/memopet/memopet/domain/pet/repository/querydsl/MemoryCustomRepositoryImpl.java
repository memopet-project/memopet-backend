//package com.memopet.memopet.domain.pet.repository.querydsl;
//
//import com.memopet.memopet.domain.pet.entity.Audience;
//import com.memopet.memopet.domain.pet.entity.Memory;
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDateTime;
//
//import static com.memopet.memopet.domain.pet.entity.QMemory.memory;
//
//public class MemoryCustomRepositoryImpl implements MemoryRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//
//    public MemoryCustomRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public long updateMemoryInfo() {
//        BooleanBuilder builder = new BooleanBuilder();
//
////        String memoryDescription = memoryInfo.getMemoryDescription();
////        LocalDateTime memoryDate = memoryInfo.getMemoryDate();
////        String title = memoryInfo.getTitle();
////        Audience audience = memoryInfo.getAudience();
////
////        if (memoryDescription != null) {
////            builder.and(memory.memoryDescription.eq(memoryDescription));
////        }
////        if (title != null) {
////            builder.and(memory.title.eq(title));
////        }
////        if (audience != null) {
////            builder.and(memory.audience.eq(audience));
////        }
////        if (memoryDate != null) {
////            builder.and(memory.memoryDate.eq(memoryDate));
////        }
////
////        return queryFactory
////                .update(memory)
////                .set(memory.memoryDescription, memoryInfo.getMemoryDescription())
////                .set(memory.title, memoryInfo.getTitle())
////                .set(memory.audience, memoryInfo.getAudience())
////                .where(memory.id.lt(memoryInfo.getId()))
////                .execute();
//        return 1;
//    }
//}
