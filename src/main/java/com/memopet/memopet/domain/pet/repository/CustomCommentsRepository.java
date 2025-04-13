package com.memopet.memopet.domain.pet.repository;

import java.util.List;

public interface CustomCommentsRepository {
        void deleteAllComments(List<Long> petIds);
}
