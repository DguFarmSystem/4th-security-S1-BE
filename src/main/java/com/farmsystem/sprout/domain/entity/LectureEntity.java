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
@Table(name = "lecture")
public class LectureEntity {
    @Id @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    private FloorEntity floor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(name = "lecture_name", nullable = false, length = 20)
    private String name;

    @Column(name = "lecture_description", nullable = false, length = 100)
    private String description;

    @Column(name = "lecture_score")
    private Integer score;

    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_status", nullable = false)
    private LectureStatus status = LectureStatus.possible;

    public enum LectureStatus {
        possible,
        impossible
    }

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<FavoriteLectureEntity> favoritedByUsers = new ArrayList<>();

}
