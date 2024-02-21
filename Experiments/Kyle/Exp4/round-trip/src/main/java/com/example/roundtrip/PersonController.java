package com.example.roundtrip;

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

    @GetMapping("/people")
    List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @PostMapping("/people")
    String addPerson(@RequestBody Person person) {

        if (person == null) {
            return failure;
        }
        personRepository.save(person);
        return success;
    }

    @GetMapping("/people/{id}")
    Person getPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }
}
