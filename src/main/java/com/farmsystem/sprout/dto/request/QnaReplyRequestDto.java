package com.farmsystem.sprout.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaReplyRequestDto {
    private String content;
    private Long adminId;
}
