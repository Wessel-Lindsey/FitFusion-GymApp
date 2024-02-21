package com.example.dualregisteration.Follow;

import com.example.dualregisteration.Business.Business;
import com.example.dualregisteration.Person.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person follower;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business followed;

    private long timestamp;

    public Follow(Person follower, Business followed, long timestamp) {
        this.follower = follower;
        this.followed = followed;
        this.timestamp = timestamp;
    }

    public Follow() {

    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getFollower() {
        return this.follower;
    }

    public void setFollower(Person follower) {
        this.follower = follower;
    }

    public Business getFollowed() {
        return followed;
    }

    public void setFollowed(Business followed) {
        this.followed = followed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
