package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
