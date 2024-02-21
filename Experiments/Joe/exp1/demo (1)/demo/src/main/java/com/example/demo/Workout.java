package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Workout {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonIgnore
    private Routine user;

    private String name;

    private int number;

    public Workout()
    {}

    public Workout(String name, int number)
    {
        this.name = name;
        this.number = number;
    }

}
