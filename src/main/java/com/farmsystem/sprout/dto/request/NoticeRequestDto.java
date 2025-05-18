package com.farmsystem.sprout.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeRequestDto {

    private String title;
    private String content;

    // 생성자나 Setter 없이 @NoArgsConstructor만 있어도 JSON → 객체 매핑됨
}
