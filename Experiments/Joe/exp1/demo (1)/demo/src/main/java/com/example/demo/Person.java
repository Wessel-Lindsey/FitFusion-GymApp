package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany //(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Routine> routines;

    private String name;

    public Person()
    {
        routines = new ArrayList<>();
    }

    public Person(String name)
    {
        this.name = name;
        routines = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void addRoutine(Routine routine)
    {
        routines.add(routine);
    }

    public List<Routine> getRoutines()
    {
        return routines;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
