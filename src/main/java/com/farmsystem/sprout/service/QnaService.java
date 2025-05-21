package com.farmsystem.sprout.service;

import com.farmsystem.sprout.domain.entity.QnaEntity;
import com.farmsystem.sprout.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    // Q&A 전체 목록 조회
    public List<QnaEntity> findAll() {
       return qnaRepository.findAll();
    }

    // Q&A 상세 조회
    public QnaEntity findById(Long id) {
        return qnaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Q&A가 존재하지 않습니다."));
    }
}


