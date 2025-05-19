package com.farmsystem.sprout.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QnaRequestDto {
    private String content;
    private Long userId;
}