package com.farmsystem.sprout.dto.response;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class QnaResponseDto {
    private Long qnaId;
    private String content;
    private String writer;
    private String status;

    // 정적 팩토리 메서드 (Entity → DTO 변환)
    public static QnaResponseDto from(QnaEntity qna) {
        return new QnaResponseDto(
                qna.getId(),
                qna.getContent(),
                qna.getUser().getLoginId(),
                qna.getStatus().name()
        );
    }
}