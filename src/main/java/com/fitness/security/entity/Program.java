package com.fitness.security.entity;

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

}