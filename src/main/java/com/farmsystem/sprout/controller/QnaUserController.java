package com.farmsystem.sprout.controller;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import com.farmsystem.sprout.domain.entity.UserEntity;
import com.farmsystem.sprout.dto.request.QnaRequestDto;
import com.farmsystem.sprout.dto.response.QnaReplyResponseDto;
import com.farmsystem.sprout.dto.response.QnaResponseDto;
import com.farmsystem.sprout.repository.QnaRepository;
import com.farmsystem.sprout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qna")
public class QnaUserController {

    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;

    // 사용자 질문 등록
    @PostMapping
    public ResponseEntity<Void> createQna(@RequestBody QnaRequestDto dto) {
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        QnaEntity qna = new QnaEntity();
        qna.setContent(dto.getContent());
        qna.setUser(user);
        qna.setStatus(QnaEntity.Status.open);

        qnaRepository.save(qna);
        return ResponseEntity.ok().build();
    }
    // 전체 질문 목록 (페이지 단위)
    @GetMapping
    public ResponseEntity<Page<QnaResponseDto>> getPagedQna(Pageable pageable) {
        Page<QnaEntity> page = qnaRepository.findAll(pageable);
        Page<QnaResponseDto> dtoPage = page.map(QnaResponseDto::new);
        return ResponseEntity.ok(dtoPage);
    }

    // 질문 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<QnaEntity> getQnaDetail(@PathVariable Long id) {
        return ResponseEntity.ok(
                qnaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("질문 없음"))
        );
    }

    // 답변 조회
    @GetMapping("/{id}/reply")
    public ResponseEntity<QnaReplyResponseDto> getReply(@PathVariable Long id) {
        QnaEntity qna = qnaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("질문 없음"));

        if (qna.getReply() == null) {
            return ResponseEntity.noContent().build();  // 답변이 아직 없음
        }

        return ResponseEntity.ok(new QnaReplyResponseDto(qna.getReply()));
    }

}
