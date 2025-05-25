package com.farmsystem.sprout.service;

import com.farmsystem.sprout.domain.entity.AdminEntity;
import com.farmsystem.sprout.domain.entity.NoticeEntity;
import com.farmsystem.sprout.dto.request.NoticeRequestDto;
import com.farmsystem.sprout.dto.response.NoticeResponseDto;
import com.farmsystem.sprout.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeResponseDto> getAllNotices() {
        return noticeRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(NoticeResponseDto::from)
                .collect(Collectors.toList());
    }

    public Long createNotice(NoticeRequestDto requestDto, AdminEntity admin) {
        NoticeEntity notice = new NoticeEntity();
        notice.setTitle(requestDto.getTitle());
        notice.setContent(requestDto.getContent());
        notice.setAdmin(admin); // 테스트용으로 임의 관리자 넣음

        NoticeEntity saved = noticeRepository.save(notice);
        return saved.getId();
    }

    public List<NoticeResponseDto> getRecentNotices(int count) {
        List<NoticeEntity> notices = noticeRepository.findTop3ByOrderByCreatedAtDesc();
        return notices.stream()
                .map(NoticeResponseDto::from)
                .collect(Collectors.toList());
    }

}

