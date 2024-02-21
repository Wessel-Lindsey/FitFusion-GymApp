package com.joeDemo.demo;

import com.joeDemo.demo.repos.RoutineRepository;
import com.joeDemo.demo.repos.UserRepository;
import com.joeDemo.demo.repos.WorkoutRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.joeDemo.demo.repos.RoutineRepository")
@ComponentScan("com.joeDemo.demo.repos.UserRepository")
//@ComponentScan("com.joeDemo.demo.repos.WorkoutRepository")
//@EnableAutoConfiguration
@SpringBootApplication
public class JoeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoeDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initUser(UserRepository userRepo, RoutineRepository routineRepo, WorkoutRepo workoutRepo)
	{
		return args -> {
			User user1 = new User("Kyle", "Kyle@gmail.com");
			Routine routine1 = new Routine("Champion Maker");
			Routine routine2 = new Routine("Beastmode");
			Workout workout1 = new Workout("pushups", 255, 255, 255);
			Workout workout2 = new Workout("squats", 900, 900, 900);
			Workout workout3 = new Workout("jumpingJacks", 9000, 9000, 9000);
			workoutRepo.save(workout1);
			workoutRepo.save(workout2);
			workoutRepo.save(workout3);
			routine1.addWorkout(workout1);
			routine1.addWorkout(workout2);
			routine2.addWorkout(workout3);
			routineRepo.save(routine1);
			routineRepo.save(routine2);
			user1.addRoutine(routine1);
			user1.addRoutine(routine2);
			userRepo.save(user1);
		};
	}

}
