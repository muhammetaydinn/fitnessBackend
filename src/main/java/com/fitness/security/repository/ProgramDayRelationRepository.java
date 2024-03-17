package com.fitness.security.repository;

import com.fitness.security.entity.ProgramDayRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDayRelationRepository extends JpaRepository<ProgramDayRelation, Long> {
}
