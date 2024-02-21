package com.joeDemo.demo;

import com.joeDemo.demo.repos.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoutineController
{

    @Autowired
    RoutineRepository routineRepo;
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/routine")
    List<Routine> getAllRoutines(){
        return routineRepo.findAll();
    }

    @GetMapping(path = "/routine/{id}")
    Routine getRoutineById( @PathVariable int id){
        return routineRepo.findById(id);
    }

    @PostMapping(path = "/routine")
    String createRoutine(Routine routine){
        if (routine == null)
            return failure;
        routineRepo.save(routine);
        return success;
    }

    @PutMapping("/routine/{id}")
    Routine updateRoutine(@PathVariable int id, @RequestBody Routine request){
        Routine routine = routineRepo.findById(id);
        if(routine == null)
            return null;
        routineRepo.save(request);
        return routineRepo.findById(id);
    }


}
