package com.fitness.security.service;

import com.fitness.security.dto.request.DayCreateRequest;
import com.fitness.security.dto.request.ExerciseCreateRequest;
import com.fitness.security.dto.request.ProgramCreateRequest;
import com.fitness.security.dto.request.SyncProgramRequest;
import com.fitness.security.dto.responses.CreateProgramResponse;
import com.fitness.security.dto.responses.ProgramDeleteResponse;
import com.fitness.security.dto.responses.sync.SyncProgramResponse;
import com.fitness.security.dto.responses.sync.SyncResDaysModel;
import com.fitness.security.dto.responses.sync.SyncResExerciseModel;
import com.fitness.security.dto.responses.sync.SyncResProgramModel;
import com.fitness.security.entity.*;
import com.fitness.security.repository.*;
import com.fitness.security.util.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    ModelMapperService modelMapperService;

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


    public ProgramDeleteResponse deleteProgram(int id) {
        try {
            //check if the program exists
            if (!programRepository.existsById(id)) {
                return ProgramDeleteResponse.builder()
                        .message("Program not found")
                        .id(id)
                        .build();
            } else {
                //delete the program
                programRepository.deleteById(id);
            }
        } catch (Exception e) {


            return ProgramDeleteResponse.builder()
                    .message(e.getMessage() + "   test")
                    .id(id)
                    .build();
        }
        return ProgramDeleteResponse.builder()
                .message("Program deleted successfully")
                .id(id)
                .build();
    }

    public SyncProgramResponse syncPrograms(SyncProgramRequest syncProgramRequest, String access_token) {
        // Get the user by the access
        User user = tokenRepository.findByToken(access_token).get().getUser();

        // get all programs of the user
        //if syncprogramrequest's id is null, create a new program
        //if syncprogramrequest's id is not null, delete the program and create a new one
        for (ProgramCreateRequest programCreateRequest : syncProgramRequest.getPrograms()) {
            if (programCreateRequest.getId().isEmpty()) {
                // Create a new program
                CreateProgramResponse createOneProgram = createOneProgram(programCreateRequest, access_token);
                System.out.println(createOneProgram);
            } else {
                // Delete the program
                ProgramDeleteResponse programDeleteResponse = deleteProgram(programCreateRequest.getId().get());
                System.out.println(programDeleteResponse);
                // Create a new program
                CreateProgramResponse createOneProgram = createOneProgram(programCreateRequest, access_token);
                System.out.println(createOneProgram);
            }
        }


        return getAllPrograms(user);

    }

    //     get all programs of a user with the given access token

    /*
{
"programs": [
{
    "id":12,
    "name":"12ahaha",
    "days":[ {
    "name": "Upper Body Day",
    "exercises": [
        {
            "setCount": 3,
            "reps": 10,
            "weightDuration": 35,
            "movementId": 5
        },
        {
            "setCount": 4,
            "reps": 8,
            "weightDuration": 40,
            "movementId": 6
        }
    ]
}]
},
{
    "id":28,
    "name":"ahaha",
    "days":[{
    "name": "Upper Body Day",
    "exercises": [
        {
            "setCount": 3,
            "reps": 10,
            "weightDuration": 35,
            "movementId": 5
        },
        {
            "setCount": 4,
            "reps": 8,
            "weightDuration": 40,
            "movementId": 6
        }
    ]
}]
},
{
    "id":15,
    "name":"15ahaha",
    "days":[{
    "name": "Upper Body Day",
    "exercises": [
        {
            "setCount": 3,
            "reps": 10,
            "weightDuration": 35,
            "movementId": 5
        },
        {
            "setCount": 4,
            "reps": 8,
            "weightDuration": 40,
            "movementId": 6
        }
    ]
}]
}
]
}
 */
    //get all programs,programDayRelations,exerciseDayRelations,exercises,days of the user and map them to the response
    public SyncProgramResponse getAllPrograms(User user) {
        //TODO: EGER KULLANICI DAYS: [] SEKLINDE GONDERIRSE PROGRAM VE RELATIONU DISINDA
        // TABLO OLUSMUYO HALIYLE BOS GELIYOR O YUZDEN DOLU GELMELI DIYE AYARLA
        //declare a new syncProgramResponse
        List<SyncResProgramModel> programs = new ArrayList<>();
        //firstly get all programs of the user from the programDayRelationRepository with user
        List<ProgramDayRelation> prDayRelationList = programDayRelationRepository.findAllByUser(user);
        //make a list of pragran_id
        List<Integer> programIdSet;
        programIdSet = prDayRelationList.stream().map(ProgramDayRelation::getProgram).map(Program::getId).toList().stream().distinct().toList();
        System.out.println("programIdset" + programIdSet);

        //then set the program names to the syncProgramResponse
        for (Integer program_id : programIdSet) {
            //Programın adını ve idsini belirliyor
            SyncResProgramModel syncProgramModel = new SyncResProgramModel();
            syncProgramModel.setId(program_id);
            syncProgramModel.setName(programRepository.findById(program_id).get().getName());


            //SET DAYS OF THE PROGRAM
            //get exerciseDayRelations of the user from the programDayRelationRepository with program_id
            List<ProgramDayRelation> prDayRelationList2 = programDayRelationRepository.findAllByUserAndProgram(user, programRepository.findById(program_id).get());
            //get a list of exerciseDayRelation_id
            List<Integer> exerciseDayRelationIdSet;
            exerciseDayRelationIdSet = prDayRelationList2.stream().map(ProgramDayRelation::getExerciseDayRelation).map(ExerciseDayRelation::getDay).map(Day::getId).toList();
            System.out.println("exerciseDayRelationIdSet" + exerciseDayRelationIdSet);
            //get day_id list from exerciseDayRelationRepository with exerciseDayRelation_id
            //find the names of the days
            List<String> dayNameSet;
            dayNameSet = exerciseDayRelationIdSet.stream().map(dayRepository::findById).map(day -> day.get().getName()).toList();
            System.out.println("dayNameSet" + dayNameSet);
            syncProgramModel.setDays(
                    dayNameSet.stream().map(dayName -> {
                        SyncResDaysModel syncResDaysModel = new SyncResDaysModel();
                        syncResDaysModel.setName(dayName);
                        //SET EXERCISES OF THE DAY
                        List<SyncResExerciseModel> exercises = new ArrayList<>();
                        //find exervises of the day
                        exercises = prDayRelationList2.stream().filter(prDayRelation -> prDayRelation.getExerciseDayRelation().getDay().getName().equals(dayName)).map(prDayRelation -> {
                            SyncResExerciseModel syncResExerciseModel = new SyncResExerciseModel();
                            syncResExerciseModel.setSetCount(prDayRelation.getExerciseDayRelation().getExercise().getSetCount());
                            syncResExerciseModel.setReps(prDayRelation.getExerciseDayRelation().getExercise().getReps());
                            syncResExerciseModel.setWeightDuration(prDayRelation.getExerciseDayRelation().getExercise().getWeightDuration());
                            syncResExerciseModel.setMovementId(prDayRelation.getExerciseDayRelation().getExercise().getMovement().getId());
                            return syncResExerciseModel;
                        }).toList();
                        syncResDaysModel.setExercises(exercises);


                        return syncResDaysModel;
                    }).toList()
            );

//


//            List<Integer> dayIdSet;
//            SyncResDaysModel syncResDaysModel = new SyncResDaysModel();
//            List<SyncResExerciseModel> exercises = new ArrayList<>();

            programs.add(syncProgramModel);
        }
        return !programs.isEmpty() ? SyncProgramResponse.builder().programs(programs).build() : null;
    }
    //then get all exercsieDayRelations of the user from the programDayRelationRepository with exercise_day_relation_id


}