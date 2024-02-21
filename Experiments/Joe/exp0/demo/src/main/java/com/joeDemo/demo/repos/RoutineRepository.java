package com.joeDemo.demo.repos;

import com.joeDemo.demo.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    Routine findById(int id);
}
