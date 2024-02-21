package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Workout> workouts;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Person person;

    private String name;

    private int number;

    public Routine()
    {
        workouts = new ArrayList<>();
    }

    public Routine(String name, int number)
    {
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //Does not check for null values
    public void addWorkout(Workout workout)
    {
        workouts.add(workout);
    }

    public List<Workout> getWorkouts()
    {
        return workouts;
    }

}
