package com.example.dualregisteration.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/people")
    List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/people/@/{username}")
    Person findPerson(@PathVariable String username) {
        return personRepository.findByUsername(username);
    }

    @PostMapping("/people")
    String addPerson(@RequestBody Person person) {
        if (person == null) {
            return failure;
        }

        personRepository.save(person);
        return success;
    }
}
