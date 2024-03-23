package com.fitness.security.service;

import com.fitness.security.dto.request.DayCreateRequest;
import com.fitness.security.dto.request.ExerciseCreateRequest;
import com.fitness.security.dto.request.ProgramCreateRequest;
import com.fitness.security.dto.responses.CreateProgramResponse;
import com.fitness.security.entity.*;
import com.fitness.security.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private ExerciseDayRelationRepository exerciseDayRelationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ProgramDayRelationRepository programDayRelationRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public CreateProgramResponse createOneProgram(ProgramCreateRequest programCreateRequest, String access_token) {
        CreateProgramResponse res = new CreateProgramResponse();
        // Create a new program
        Program program = new Program();
        // Set the name of the program
        program.setName(programCreateRequest.getName());

        // Set the program
        Program save = programRepository.save(program);

        for (DayCreateRequest dayData : programCreateRequest.getDays()) {
            Day day = new Day();
            day.setName(dayData.getName());
            day = dayRepository.save(day);

            for (ExerciseCreateRequest exerciseData : dayData.getExercises()) {
                Exercise exercise = new Exercise();
                exercise.setSetCount(exerciseData.getSetCount());
                exercise.setReps(exerciseData.getReps());
                exercise.setWeightDuration(exerciseData.getWeightDuration());
                // TODO: MAYBE ADD A TRY CATCH BLOCK
                Movement movement = movementRepository.findById(exerciseData.getMovementId()).orElse(null);
                exercise.setMovement(movement);
                exercise = exerciseRepository.save(exercise);

                ExerciseDayRelation dayExercise = new ExerciseDayRelation();
                dayExercise.setDay(day);
                dayExercise.setExercise(exercise);
                dayExercise = exerciseDayRelationRepository.save(dayExercise);

                ProgramDayRelation programDayRelation = new ProgramDayRelation();
                programDayRelation.setUser(
                        // find the user by access token
                        tokenRepository.findByToken(access_token).get().getUser()

                ); // Kullanıcı nesnesini ayarla
                programDayRelation.setProgram(program); // Program nesnesini ayarla
                programDayRelation.setExerciseDayRelation(dayExercise); // DayExercise nesnesini ayarla
                programDayRelationRepository.save(programDayRelation);

            }
        }
        System.out.println("Program created successfully");
        // return the program
        return CreateProgramResponse.builder()
                .id(save.getId())
                .message("Program created successfully")
                .status(true)
                .build();

    }

}