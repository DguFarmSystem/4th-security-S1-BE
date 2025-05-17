package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class NoticeEntity {
    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "notice_title", nullable = false, length = 100)
    private String title;

    @Column(name = "notice_content", nullable = false, length = 200)
    private String content;

    @Column(name = "notice_created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminEntity admin;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
