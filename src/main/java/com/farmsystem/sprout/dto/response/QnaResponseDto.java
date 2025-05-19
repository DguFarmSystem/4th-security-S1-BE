package com.farmsystem.sprout.dto.response;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaResponseDto {
    private Long qnaId;
    private String content;
    private String writer;
    private String status;

    public QnaResponseDto(QnaEntity qna) {
        this.qnaId = qna.getId();
        this.content = qna.getContent();
        this.writer = qna.getUser().getLoginId();
        this.status = qna.getStatus().name();
    }
}