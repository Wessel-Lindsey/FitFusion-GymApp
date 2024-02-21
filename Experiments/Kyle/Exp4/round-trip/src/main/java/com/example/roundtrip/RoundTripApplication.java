package com.example.roundtrip;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoundTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoundTripApplication.class, args);
	}


	@Bean
    CommandLineRunner initUser(PersonRepository personRepository) {
        return args -> {
            Person person = new Person("david nalbandian", "123");
            personRepository.save(person);
        };
    }
}
