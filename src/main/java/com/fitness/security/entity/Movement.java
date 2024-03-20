package com.fitness.security.entity;

import com.fitness.security.util.JsonToListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name = "movement")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String forceType;

    private String level;

    private String mechanic;

    private String equipment;
    @Column(columnDefinition = "json")
    private String primaryMuscles;
    @Column(columnDefinition = "json")
    private String secondaryMuscles;
    @Column(columnDefinition = "json")
    private String instructions;

    private String category;

    @OneToMany(mappedBy = "movement", cascade = CascadeType.ALL)
    private List<Exercise> exercises;


    public List<String> getPrimaryMusclesList() throws Exception {
        return JsonToListConverter.convertJsonToList(primaryMuscles);
    }

    public List<String> getSecondaryMusclesList() throws Exception {
        return JsonToListConverter.convertJsonToList(secondaryMuscles);
    }

    public List<String> getInstructionsList() throws Exception {
        return JsonToListConverter.convertJsonToList(instructions);
    }


}
