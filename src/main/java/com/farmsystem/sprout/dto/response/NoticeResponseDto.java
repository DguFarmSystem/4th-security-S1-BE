package com.farmsystem.sprout.dto.response;

import com.farmsystem.sprout.domain.entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NoticeResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    // 정적 팩토리 메서드 (Entity → DTO 변환용)
    public static NoticeResponseDto from(NoticeEntity entity) {
        return new NoticeResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt()
        );
    }
}
