package com.memopet.memopet.domain.pet.respository;

import com.memopet.memopet.domain.pet.entity.Comment;
import com.memopet.memopet.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
