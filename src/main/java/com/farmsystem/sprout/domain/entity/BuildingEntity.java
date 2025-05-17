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
@Table(name = "building")
public class BuildingEntity {
    @Id @GeneratedValue
    @Column(name = "building_id")
    private Long id;

    @Column(name = "building_name", nullable = false, length = 20)
    private String name;

    @Column(name = "building_location", nullable = false, length = 50)
    private String location;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<FloorEntity> floors = new ArrayList<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<LectureEntity> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<StudyRoomEntity> studyRooms = new ArrayList<>();
}
