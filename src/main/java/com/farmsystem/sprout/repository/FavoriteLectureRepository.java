package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.FavoriteLectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteLectureRepository extends JpaRepository<FavoriteLectureEntity, Long> {
}
