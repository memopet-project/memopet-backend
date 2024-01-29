package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

}
