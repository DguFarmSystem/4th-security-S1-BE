package com.farmsystem.sprout.service;

import com.farmsystem.sprout.domain.entity.AdminEntity;
import com.farmsystem.sprout.domain.entity.QnaEntity;
import com.farmsystem.sprout.domain.entity.QnaReplyEntity;
import com.farmsystem.sprout.repository.QnaRepository;
import com.farmsystem.sprout.repository.QnaReplyRepository;
import com.farmsystem.sprout.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaRepository qnaRepository;
    private final QnaReplyRepository qnaReplyRepository;
    private final AdminRepository adminRepository;

    // 답변 등록
    public void writeReply(Long qnaId, String content, Long adminId) {
        QnaEntity qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new RuntimeException("Q&A가 존재하지 않습니다."));

        if (qna.getReply() != null) {
            throw new IllegalStateException("이미 답변이 등록된 Q&A입니다.");
        }

        AdminEntity admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("관리자를 찾을 수 없습니다."));

        QnaReplyEntity reply = new QnaReplyEntity();
        reply.setContent(content);
        reply.setAdmin(admin);
        reply.setQna(qna);  // 연관관계 설정

        qna.setReply(reply);  // Qna에도 설정
        qna.setStatus(QnaEntity.Status.close);

        qnaReplyRepository.save(reply);
    }
}
