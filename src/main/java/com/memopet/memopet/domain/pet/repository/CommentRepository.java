package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
