package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
