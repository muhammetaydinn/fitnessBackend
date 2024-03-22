package com.fitness.security.repository;

import com.fitness.security.entity.ExerciseDayRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseDayRelationRepository extends JpaRepository<ExerciseDayRelation, Integer> {
}