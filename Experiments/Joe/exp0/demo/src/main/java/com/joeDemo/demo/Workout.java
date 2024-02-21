package com.joeDemo.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Workout {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    String name;

    //WorkoutType workoutType;

    //Thought process is to have the opprotunity to have
    int num1;
    int num2;
    int num3;

    public Workout() {

    }

    public Workout(String name, /*WorkoutType workoutType,*/ int num1, int num2, int num3) {
        this.name = name;
        //this.workoutType = workoutType;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    //GETTERS AND SETTERS
    //-------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public WorkoutType getWorkoutType() {
//        return workoutType;
//    }
//
//    public void setWorkoutType(WorkoutType workoutType) {
//        this.workoutType = workoutType;
//    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }
}
