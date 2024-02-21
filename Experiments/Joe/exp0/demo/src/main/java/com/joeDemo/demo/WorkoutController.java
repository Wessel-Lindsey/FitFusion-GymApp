package com.joeDemo.demo;

import com.joeDemo.demo.repos.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkoutController
{

    @Autowired
    public WorkoutRepo workoutRepo;
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/workouts")
    List<Workout> getAllWorkouts(){
        return workoutRepo.findAll();
    }

    @GetMapping(path = "/workouts/{id}")
    Workout getPhoneById( @PathVariable int id){
        return workoutRepo.findById(id);
    }

    @PostMapping(path = "/workouts")
    String createWorkout(Workout workout){
        if (workout == null)
            return failure;
        workoutRepo.save(workout);
        return success;
    }

    @PutMapping("/workouts/{id}")
    Workout updateWorkout(@PathVariable int id, @RequestBody Workout request){
        Workout workout = workoutRepo.findById(id);
        if(workout == null)
            return null;
        workoutRepo.save(request);
        return workoutRepo.findById(id);
    }


}
