package com.farmsystem.sprout.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    private Long userNumber;
}
