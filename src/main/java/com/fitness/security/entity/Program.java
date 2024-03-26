package com.fitness.security.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Table(name = "program")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<ProgramDayRelation> programDayRelations;

    
    

}