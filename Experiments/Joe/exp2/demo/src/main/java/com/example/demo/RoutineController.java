package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @PostMapping(path = "/people/{userID}/routine")
    String createPersonRoutine(@PathVariable int userID, @RequestBody Routine routine)
    {
        if(routine == null)
            return failure;

        Person tempPerson = personRepository.findById(userID);

        if(tempPerson == null)
            return "user not found";

        tempPerson.addRoutine(routine);

        routineRepository.save(routine);
        personRepository.save(tempPerson);

        return success;

    }



    @GetMapping(path = "people/{userID}/routine")
    List<Routine> getPersonRoutines(@PathVariable int userID)
    {
        Person tempPerson = personRepository.findById(userID);

        if(tempPerson == null)
            return null;

        return tempPerson.getRoutines();

    }

}
