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

    @PostMapping(path = "/people")
    String createPerson(@RequestBody Person person){
        if (person == null)
            return failure;
        personRepository.save(person);
        return success;
    }

    @GetMapping(path = "/people")
    List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping(path = "/people/{userID}")
    Person getPersonbyId(@PathVariable int userID)
    {
        Person tempPerson = personRepository.findById(userID);

        if(tempPerson == null)
            return null;

        return tempPerson;
    }

    /*@GetMapping(path = "/people/name/{name}")
    Person getPersonbyName(@PathVariable String name)
    {
        Person tempPerson = personRepository.findByName(name);

        if(tempPerson == null)
            return null;

        return tempPerson;
    }*/

    /*@PostMapping(path = "/person2")
    String getBoss(@RequestBody Person person)
    {
        int jip = person.getId();
        personRepository.save(person);
        return person.getName();
    }*/


}
