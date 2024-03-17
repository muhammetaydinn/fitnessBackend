package com.fitness.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Table(name = "program")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}