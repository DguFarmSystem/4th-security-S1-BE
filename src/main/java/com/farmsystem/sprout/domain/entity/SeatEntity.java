package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "seat")
public class SeatEntity {
    @Id @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_room_id", nullable = false)
    private StudyRoomEntity studyRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "floor_id", nullable = false)
    private Long floorId;

    @Column(name = "building_id", nullable = false)
    private Long buildingId;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_status", nullable = false)
    private SeatStatus status = SeatStatus.possible;

    @Column(name = "seat_start_time")
    private LocalDateTime startTime;

    @Column(name = "seat_end_time")
    private LocalDateTime endTime;

    public enum SeatStatus {
        possible,
        impossible
    }
}
