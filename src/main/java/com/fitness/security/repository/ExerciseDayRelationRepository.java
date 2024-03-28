package com.fitness.security.repository;

import com.fitness.security.entity.Day;
import com.fitness.security.entity.ExerciseDayRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseDayRelationRepository extends JpaRepository<ExerciseDayRelation, Integer> {
    List<ExerciseDayRelation> findAllByDay(Day day);

    ExerciseDayRelation findByDay(Day day);
}