package com.friends.friendfinder.Person;

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

    @GetMapping(path = "/people")
    List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping(path = "/findBobs")
    List<Person> getAllBobs() {
        return personRepository.findPersonsByFirstName("Bob");
    }

    @GetMapping(path = "/people/{id}")
    Person getPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }
    

    @GetMapping(path = "/people/{id}/friends")
    List<Person> getFriendsOfPerson(@PathVariable int id) {
        Person person = personRepository.findById(id);
        String city = person.getLocation().getCity();
        return personRepository.findOtherPersonsInCity(city, id);
    }

    @PostMapping(path = "/people")
    String createPerson(@RequestBody Person person) {
        if (person == null) {
            return failure;
        }
        personRepository.save(person);
        return success; 

    }


}
