package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
}
