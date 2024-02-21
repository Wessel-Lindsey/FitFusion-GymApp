package com.joeDemo.demo.repos;

import com.joeDemo.demo.Showcase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowcaseRepo extends JpaRepository<Showcase, Long> {
        Showcase findById(int id);
        void deleteById(int id);
}
