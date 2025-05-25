package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<QnaEntity, Long> {
    // 최근 3개 질문 조회
    List<QnaEntity> findTop3ByOrderByCreatedAtDesc();
}


