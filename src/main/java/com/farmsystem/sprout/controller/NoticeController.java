package com.farmsystem.sprout.controller;

import com.farmsystem.sprout.domain.entity.AdminEntity;
import com.farmsystem.sprout.dto.request.NoticeRequestDto;
import com.farmsystem.sprout.dto.response.NoticeResponseDto;
import com.farmsystem.sprout.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeResponseDto>> getAllNotices() {
        return ResponseEntity.ok(noticeService.getAllNotices());
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody NoticeRequestDto requestDto) {
        // 테스트용 관리자 객체
        AdminEntity fakeAdmin = new AdminEntity();
        fakeAdmin.setId(1L);  // DB에 존재하는 admin_id 있어야 함

        Long id = noticeService.createNotice(requestDto, fakeAdmin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("공지사항 등록 완료! ID: " + id);
    }

    // 최근 공지사항 3개 조회 (메인페이지용)
    @GetMapping("/main")
    public ResponseEntity<List<NoticeResponseDto>> getRecentNoticesForMain() {
        List<NoticeResponseDto> recentNotices = noticeService.getRecentNotices(3); // 최근 공지사항 3개
        return ResponseEntity.ok(recentNotices);
    }
}
