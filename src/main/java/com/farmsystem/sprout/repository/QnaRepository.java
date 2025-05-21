package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import com.farmsystem.sprout.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {
    List<QnaEntity> findByUser(UserEntity user);
}
