package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    List<NoticeEntity> findAllByOrderByCreatedAtDesc();
    List<NoticeEntity> findTop3ByOrderByCreatedAtDesc(); // 메인페이지에 나타낼 공지사항 3개 고정
}
