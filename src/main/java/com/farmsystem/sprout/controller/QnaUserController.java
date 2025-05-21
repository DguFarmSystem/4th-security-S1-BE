package com.farmsystem.sprout.controller;

import com.farmsystem.sprout.domain.User;
import com.farmsystem.sprout.domain.entity.QnaEntity;
import com.farmsystem.sprout.domain.entity.UserEntity;
import com.farmsystem.sprout.mapper.UserMapper;
import com.farmsystem.sprout.repository.QnaRepository;
import com.farmsystem.sprout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaUserController {

    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<QnaEntity> createQna(@RequestBody QnaEntity qnaEntity, @RequestParam String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        qnaEntity.setUser(userEntity);
        QnaEntity savedQna = qnaRepository.save(qnaEntity);
        return ResponseEntity.ok(savedQna);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<QnaEntity>> getUserQnas(@PathVariable String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<QnaEntity> qnas = qnaRepository.findByUser(userEntity);
        return ResponseEntity.ok(qnas);
    }

    // 전체 질문 목록 (페이지 단위)
    @GetMapping
    public ResponseEntity<Page<QnaEntity>> getPagedQna(Pageable pageable) {
        Page<QnaEntity> page = qnaRepository.findAll(pageable);
        return ResponseEntity.ok(page);
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
    public ResponseEntity<QnaEntity> getReply(@PathVariable Long id) {
        QnaEntity qna = qnaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("질문 없음"));

        if (qna.getReply() == null) {
            return ResponseEntity.noContent().build();  // 답변이 아직 없음
        }

        return ResponseEntity.ok(qna);
    }

}
