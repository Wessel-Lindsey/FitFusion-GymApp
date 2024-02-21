package com.example.dualregisteration.Follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dualregisteration.Business.Business;
import com.example.dualregisteration.Business.BusinessRepository;
import com.example.dualregisteration.Person.Person;
import com.example.dualregisteration.Person.PersonRepository;

@RestController
public class FollowController {

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @Autowired
    FollowRepository followRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    BusinessRepository businessRepository;

    @GetMapping("/follows")
    List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    @PostMapping("/people/@/{username}/follow/{businessName}")
    String follow(@PathVariable String businessName, @PathVariable String username) {
        Person following = personRepository.findByUsername(username);
        Business followed = businessRepository.findByBusinessName(businessName);
        long timestamp = System.currentTimeMillis();
        Follow follow = new Follow(following, followed, timestamp);
        followRepository.save(follow);
        return success;
    }

    @GetMapping("/people/@/{username}/follows")
    List<Follow> getFollowsOfFollower(@PathVariable String username) {
        return followRepository.getFollowsByFollower(username);
    }

    @GetMapping("/businesses/@/{businessName}/followed")
    List<Follow> getFollowsOfBusiness(@PathVariable String businessName) {
        return followRepository.getFollowsByFollowed(businessName);
    }

}
