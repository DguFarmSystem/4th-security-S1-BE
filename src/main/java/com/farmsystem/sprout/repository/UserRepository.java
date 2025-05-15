package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
