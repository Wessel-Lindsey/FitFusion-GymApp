package com.friends.friendfinder.Location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping("/locations")
    List<Location> getAllLocations() {
        return locationRepository.findAll();
    } 

    @PostMapping("/locations")
    String createLocation(@RequestBody Location location) {
        if (location == null) {
            return failure;
        }
        locationRepository.save(location);
        return success;
    }
}
