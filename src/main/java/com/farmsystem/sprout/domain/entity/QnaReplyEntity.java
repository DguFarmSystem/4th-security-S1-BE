package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class QnaReplyEntity {
    @Id @GeneratedValue
    @Column(name = "qna_reply_id")
    private Long id;

    @Column(name = "qna_reply_content", nullable = false, length = 200)
    private String content;

    @Column(name = "qna_created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "qna_id", nullable = false)
    private QnaEntity qna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminEntity admin;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
