package com.joeDemo.demo;

import javax.persistence.*;
import java.util.List;


@Entity
public class User
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;



    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "showcase_id")
    private Showcase showcase;*/

    @OneToMany
    private List<Routine> routines;

    /*public User(Showcase showcase, List<Workout> workouts) {
        this.showcase = showcase;
        //this.workouts = new ArrayList<Workout>();
    }*/

    public User(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public User()
    {}

    /*public Showcase getShowcase() {
        return showcase;
    }*/

    //GETTERS ANS SETTTERS
    //-------------------------------

    /*public void setShowcase(Showcase showcase) {
        this.showcase = showcase;
    }*/

    /*public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }*/

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void addRoutine(Routine routine)
    {
        routines.add(routine);
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
}
