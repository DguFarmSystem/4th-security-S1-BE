package com.farmsystem.sprout.dto.response;


import com.farmsystem.sprout.domain.entity.QnaReplyEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QnaReplyResponseDto {
    private Long replyId;
    private String content;
    private String responder;
    private LocalDateTime repliedAt;

    public QnaReplyResponseDto(QnaReplyEntity reply) {
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.responder = reply.getAdmin().getUser().getLoginId();
        this.repliedAt = reply.getCreatedAt();
    }
}