package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "favorite_study_room")
public class FavoriteStudyRoomEntity {
    @Id @GeneratedValue
    @Column(name = "favorite_study_room_id")
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
}
