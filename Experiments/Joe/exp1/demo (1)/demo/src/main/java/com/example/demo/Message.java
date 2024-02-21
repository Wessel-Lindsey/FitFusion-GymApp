package com.example.demo;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;

public class Message {

    Person sender;

    Blob media;

    Date date;

    Time time;

    public Message(Person sender, Blob media, Date date, Time time) {
        this.sender = sender;
        this.media = media;
        this.date = date;
        this.time = time;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Blob getMedia() {
        return media;
    }

    public void setMedia(Blob media) {
        this.media = media;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
