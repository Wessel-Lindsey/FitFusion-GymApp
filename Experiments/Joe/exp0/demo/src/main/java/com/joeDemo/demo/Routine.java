package com.joeDemo.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Routine
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //This can be used to look up the user's basic information and give users info about who
    //Created the routine if it is shared
    private String creatorUserName;

    private Date creationDate;

    @OneToMany
    private List<Workout> workouts;

    public Routine(String name)
    {
        this.name = name;
    }


    public Routine(String name, String creatorUserName, Date creationDate) {
        this.name = name;
        this.creatorUserName = creatorUserName;
        this.creationDate = creationDate;
        workouts = new ArrayList<>();
    }

    public Routine() {
    }

    //GETTERS AND SETTERS
    //---------------------------------------------

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

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void addWorkout(Workout workout)
    {
        workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
