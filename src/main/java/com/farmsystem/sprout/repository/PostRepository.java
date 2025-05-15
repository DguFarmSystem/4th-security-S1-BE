package com.farmsystem.sprout.repository;

import com.farmsystem.sprout.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
