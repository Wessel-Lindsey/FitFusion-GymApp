package com.friends.friendfinder.Routine;

import java.util.List;

import com.friends.friendfinder.Exercise.Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int repeatsEvery;
    private String name;

    @OneToMany
    private List<Exercise> exercises;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepeatsEvery() {
        return this.repeatsEvery;
    }

    public void setRepeatsEvery(int repeatsEvery) {
        this.repeatsEvery = repeatsEvery;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }


}
