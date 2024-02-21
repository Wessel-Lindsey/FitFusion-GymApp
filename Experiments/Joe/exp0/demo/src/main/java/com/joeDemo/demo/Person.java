package com.joeDemo.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Person {

    private String name;

    //private List<String> workouts;

    private int pushups;

    //private int te;

    private HashMap<String, Integer> pushupsThisWeek = new HashMap<String, Integer>();

    public Person()
    {}

    public Person(String name, int pushups)
    {
        this.name = name;
        ///this.workouts = workouts;
        this.pushups = pushups;
    }

    public String getName()
    {
        return name;
    }


    /*public List<String> getWorkout()
    {
        return workouts;
    }

    public String getWorkoutString()
    {
        return workouts.toString();
    }
    */

    public void addPushupWorkout(String date, int num)
    {

        if(num > pushups)
        {
            pushups = num;
        }

        pushupsThisWeek.put(date, num);
    }

    public void deletePushupWorkout(String date)
    {
        pushupsThisWeek.remove(date);
    }

    public int getPushupWorkout(String date)
    {
        return pushupsThisWeek.get(date);
    }

    public HashMap<String, Integer> getAllPushupWorkout()
    {
        return pushupsThisWeek;
    }

    public int getPushups()
    {
        return pushups;
    }

    public void setPushups(int givenPushups)
    {
        pushups = givenPushups;
    }


    public void workoutReplace(int index, String replace)
    {

    }


}
