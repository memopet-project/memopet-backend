package com.memopet.memopet.domain.pet.repository;

import com.memopet.memopet.domain.pet.entity.MemoryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MemoryImageRepository  extends JpaRepository<MemoryImage, Long> {

}
