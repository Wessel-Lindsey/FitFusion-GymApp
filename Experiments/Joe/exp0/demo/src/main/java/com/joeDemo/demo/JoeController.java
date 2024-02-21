package com.joeDemo.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class JoeController {

    HashMap<String, Person> peopleList = new HashMap<String, Person>();

    @GetMapping("/")
    public String getTest(@RequestParam(value="username", defaultValue = "chungus") String message)
    {
        return String.format("Hello, %s!", message);
    }

    @PostMapping("/addPerson")
    public String createPerson(@RequestBody Person person)
    {
        System.out.println(person);
        peopleList.put(person.getName(), person);
        return "New person "+ person.getName() + " Saved, workouts: " //+ person.getWorkoutString()
                + ", pushups: " + person.getPushups();

    }

    @GetMapping("/people")
    public @ResponseBody HashMap<String,Person> getAllPersons()
    {
        return peopleList;
    }

    @PutMapping("/people/{firstName}")
    public @ResponseBody Person updatePerson(@PathVariable String firstName, @RequestBody Person p) {
        peopleList.replace(firstName, p);
        return peopleList.get(firstName);
    }

    @DeleteMapping("/people/{firstName}")
    public @ResponseBody HashMap<String, Person> deletePerson(@PathVariable String firstName) {
        peopleList.remove(firstName);
        return peopleList;
    }

    @GetMapping("/getPushupCompare")
    public String getPushupCompare(@RequestParam(value="user1", defaultValue = "null1") String user1Name,
                               @RequestParam(value="user2", defaultValue = "null2") String user2Name)
    {

        if(user1Name.equals("null1"))
        {
            throw new RuntimeException("No name for user1");
        }

        if(user2Name.equals("null2"))
        {
            throw new RuntimeException("No name for user2");
        }

        Person user1 = peopleList.get(user1Name);
        Person user2 = peopleList.get(user2Name);

        if(user1 == null)
        {
            throw new RuntimeException("user1 is null");
        }

        if(user2 == null)
        {
            throw new RuntimeException("user2 is null");
        }

        int compareVal = user1.getPushups() - user2.getPushups();

        if(compareVal > 0)
        {
            return user1Name + " is stronger";
        }
        else if(compareVal < 0)
        {
            return user2Name + " is stronger";
        }
        else
        {
            return "evenly matched opponents";
        }

    }

    @GetMapping("/person/{firstName}")
    public @ResponseBody HashMap<String, Integer> controllerGetAllPushupWorkout(@PathVariable String firstName)
    {
        Person p = peopleList.get(firstName);
        return p.getAllPushupWorkout();
    }

    @PutMapping("/person/{firstName}/{date}/{pushupsThisWorkout}")
    public @ResponseBody Person controllerAddPushupWorkout(@PathVariable String firstName,
                                                 @PathVariable String date, @PathVariable int pushupsThisWorkout)
    {
        Person p = peopleList.get(firstName);
        p.addPushupWorkout(date, pushupsThisWorkout);
        return peopleList.get(firstName);
    }

    @DeleteMapping("/person/{firstName}/{date}")
    public String controllerDeletePushupWorkout(@PathVariable String firstName,
                                                @PathVariable String date)
    {
        Person p = peopleList.get(firstName);
        p.deletePushupWorkout(date);
        return "deleted workout on " + date;
    }

    /*@GetMapping("/person/getMaxPushupsThisWeek/{firstName}")S
    public int getMaxPushupsThisWeek(@PathVariable String firstName)
    {

    }*/

}
