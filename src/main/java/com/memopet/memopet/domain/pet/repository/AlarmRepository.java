package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
