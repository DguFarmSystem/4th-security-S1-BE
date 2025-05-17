package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "qna")
public class QnaEntity {
    @Id @GeneratedValue
    @Column(name = "qna_number")
    private Long id;

    @Column(name = "qna_content", nullable = false, length = 200)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "qna_status", nullable = false)
    private Status status = Status.open;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public enum Status {
        open,
        close
    }

    @OneToOne(mappedBy = "qna", cascade = CascadeType.ALL)
    private QnaReplyEntity reply;
}
