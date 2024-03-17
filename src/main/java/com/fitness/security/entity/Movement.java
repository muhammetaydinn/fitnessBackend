package com.fitness.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "movement")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String forceType;

    private String level;

    private String mechanic;

    private String equipment;

    private String primaryMuscles;

    private String secondaryMuscles;

    private String instructions;

    private String category;

    @OneToMany(mappedBy = "movement", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

}
