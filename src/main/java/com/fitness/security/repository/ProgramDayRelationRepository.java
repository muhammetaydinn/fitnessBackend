package com.fitness.security.repository;

import com.fitness.security.entity.Program;
import com.fitness.security.entity.ProgramDayRelation;
import com.fitness.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramDayRelationRepository extends JpaRepository<ProgramDayRelation, Integer> {

    List<ProgramDayRelation> findAllByUser(User user);

    List<ProgramDayRelation> findAllByUserAndProgram(User user, Program program);

    // List<ProgramDayRelation> findAllByDayId(Integer dayId);

}
