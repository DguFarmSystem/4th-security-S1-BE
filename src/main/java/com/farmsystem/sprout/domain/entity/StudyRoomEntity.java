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
@Table(name = "study_room")
public class StudyRoomEntity {
    @Id @GeneratedValue
    @Column(name = "study_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    private FloorEntity floor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(name = "study_room_name", nullable = false, length = 20)
    private String name;

    @Column(name = "study_room_description", length = 100)
    private String description;

    @OneToMany(mappedBy = "studyRoom", cascade = CascadeType.ALL)
    private List<SeatEntity> seats = new ArrayList<>();

    @OneToMany(mappedBy = "studyRoom", cascade = CascadeType.ALL)
    private List<FavoriteStudyRoomEntity> favoredByUsers = new ArrayList<>();

    @OneToMany(mappedBy = "studyRoom", cascade = CascadeType.ALL)
    private List<CommunityEntity> communityPosts = new ArrayList<>();
}
