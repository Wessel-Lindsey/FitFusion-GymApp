package com.joeDemo.demo;

import com.joeDemo.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    /*@Autowired
    ShowcaseRepo showcaseRepo;
    */

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepo.findById(id);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepo.save(user);
        return success;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepo.findById(id);
        if(user == null)
            return null;
        userRepo.save(request);
        return userRepo.findById(id);
    }

    /*@PutMapping("/users/{userId}/showcases/{showcaseId}")
    String assignShowcaseToUser(@PathVariable int userId,@PathVariable int showcaseId){
        User user = userRepo.findById(userId);
        Showcase showcase = showcaseRepo.findById(showcaseId);
        if(user == null || showcase == null)
            return failure;
        showcase.setUser(user);
        //user.setShowcase(showcase);
        userRepo.save(user);
        return success;
    }*/

    /*@DeleteMapping(path = "/users/{id}")
    String deleteShowcase(@PathVariable int id){
        userRepo.deleteById(id);
        return success;
    }*/


}
