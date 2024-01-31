package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Blocked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedRepository extends JpaRepository<Blocked, Long> {

}
