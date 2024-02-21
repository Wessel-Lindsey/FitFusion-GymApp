package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @PostMapping(path = "/persons")
    String createPerson(@RequestBody Person person){
        if (person == null)
            return failure;
        personRepository.save(person);
        return success;
    }

    @GetMapping(path = "/persons")
    List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    /*@PostMapping(path = "/person2")
    String getBoss(@RequestBody Person person)
    {
        int jip = person.getId();
        personRepository.save(person);
        return person.getName();
    }*/


}
