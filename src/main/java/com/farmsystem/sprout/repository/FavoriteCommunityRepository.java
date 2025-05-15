package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.FavoriteCommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCommunityRepository extends JpaRepository<FavoriteCommunityEntity, Long> {
}
