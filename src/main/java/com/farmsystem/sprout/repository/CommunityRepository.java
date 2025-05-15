package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
}
