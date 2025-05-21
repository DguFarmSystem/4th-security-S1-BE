package com.farmsystem.sprout.controller;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import com.farmsystem.sprout.dto.request.QnaReplyRequestDto;
import com.farmsystem.sprout.service.QnaReplyService;
import com.farmsystem.sprout.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final QnaService qnaService;
    private final QnaReplyService qnaReplyService;

    // Q&A 전체 목록 조회
    @GetMapping("/qna")
    public ResponseEntity<List<QnaEntity>> getAllQna() {
        return ResponseEntity.ok(qnaService.findAll());
    }

    // Q&A 상세 조회
    @GetMapping("/qna/{id}")
    public ResponseEntity<QnaEntity> getQnaDetail(@PathVariable Long id) {
        return ResponseEntity.ok(qnaService.findById(id));
    }

    // Q&A 답변 작성
    @PostMapping("/qna/{id}/reply")
    public ResponseEntity<Void> replyToQna(
            @PathVariable Long id,
            @RequestBody QnaReplyRequestDto dto
    ) {
        qnaReplyService.writeReply(id, dto.getContent(), dto.getAdminId());
        return ResponseEntity.ok().build();
    }
}
