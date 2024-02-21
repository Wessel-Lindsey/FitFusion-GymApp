package com.example.tennisfanapp.Players;

public class Player {
    private String firstName;
    private String lastName;
    private int ranking;
    private int age;

    public Player() {
    }

    public Player(String firstName, String lastName, int ranking, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRanking() {
        return ranking;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
