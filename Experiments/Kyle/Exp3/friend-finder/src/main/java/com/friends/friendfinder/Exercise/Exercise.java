package com.friends.friendfinder.Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int reps;
    private int personalRecord;

    public Exercise(String name, int reps, int personalRecord) {
        this.name = name;
        this.reps = reps;
        this.personalRecord = personalRecord;
    }

    public Exercise() {

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return this.reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getPersonalRecord() {
        return this.personalRecord;
    }

    public void setPersonalRecord(int personalRecord) {
        this.personalRecord = personalRecord;
    }

}
