package com.fitness.security.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exercise_day_relation")
public class ExerciseDayRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Day day;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Exercise exercise;

}