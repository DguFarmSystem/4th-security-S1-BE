package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "floor")
public class FloorEntity {
    @Id @GeneratedValue
    @Column(name = "floor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(name = "floor_number", nullable = false)
    private int number;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<LectureEntity> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<StudyRoomEntity> studyRooms = new ArrayList<>();
}
