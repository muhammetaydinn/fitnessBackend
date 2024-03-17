package com.fitness.security.service;

import com.fitness.security.repository.ProgramDayRelationRepository;
import com.fitness.security.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;

    // Gerekli servis metotları buraya gelecek
    private final ProgramDayRelationRepository programDayRelationRepository;
    private final DayService dayService;
    private final ExerciseService exerciseService;


//    public void addProgram(ProgramRequestDTO programDTO) {
//        // Programı kaydet
//
//    }
}