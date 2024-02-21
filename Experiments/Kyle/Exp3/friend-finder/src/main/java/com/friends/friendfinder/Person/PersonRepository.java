package com.friends.friendfinder.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findById(int id);

    @Query("SELECT p FROM Person p WHERE p.firstName = :firstName")
    List<Person> findPersonsByFirstName(String firstName);
 
    @Query("SELECT p FROM Person p WHERE (p.location.city = :city AND p.id != :id)")
    List<Person> findOtherPersonsInCity(String city, int id);

}
