package com.fitness.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exercise_day_relation")
public class ExerciseDayRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Day day;

    @ManyToOne
    private Exercise exercise;
}