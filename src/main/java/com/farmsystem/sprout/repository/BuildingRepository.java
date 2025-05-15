package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
}
