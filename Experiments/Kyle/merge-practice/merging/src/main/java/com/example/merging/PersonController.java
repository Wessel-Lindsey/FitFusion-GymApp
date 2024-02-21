package com.example.merging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";


    @GetMapping("/persons") 
    List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    Person getPerson(@PathVariable int id) {
        return personRepository.getById(id);
    }

    @PostMapping("/persons")
    String addPerson(@RequestBody Person person) {
        if (person == null ) {
            return failure;
        }
        personRepository.save(person);
        return success;
    }

    @GetMapping("/smartpeople")
    List<Person> getSmartPeople() {
        return personRepository.findByIqGreaterThan(150);    
    }

    

}
