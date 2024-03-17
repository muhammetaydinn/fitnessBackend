package com.fitness.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "program_day_relation")
public class ProgramDayRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExerciseDayRelation exerciseDayRelation;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
