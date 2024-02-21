package com.joeDemo.demo.repos;

import com.joeDemo.demo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Long>
{

    User findByUsername(String Username);

    User findById(int id);

    @Transactional
    void deleteById(int id);

}
