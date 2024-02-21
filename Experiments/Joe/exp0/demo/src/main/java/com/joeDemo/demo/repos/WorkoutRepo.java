package com.joeDemo.demo.repos;

import com.joeDemo.demo.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Long> {
    Workout findById(int id);
}
