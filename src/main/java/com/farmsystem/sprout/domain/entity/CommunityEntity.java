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
@Table(name = "community")
public class CommunityEntity {
    @Id @GeneratedValue
    @Column(name = "community_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_room_id", nullable = false)
    private StudyRoomEntity studyRoom;

    @Column(name = "floor_id", nullable = false)
    private Long floorId;

    @Column(name = "building_id", nullable = false)
    private Long buildingId;

    @Column(name = "community_title", nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<FavoriteCommunityEntity> favoritedByUsers = new ArrayList<>();
}
