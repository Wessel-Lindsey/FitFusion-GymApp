package com.example.dualregisteration.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @Autowired
    BusinessRepository businessRepository;

    @GetMapping("/businesses")
    List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    @GetMapping("/businesses/@/{businessName}")
    Business findBusiness(@PathVariable String businessName) {
        return businessRepository.findByBusinessName(businessName);
    }

    @GetMapping("businesses/near/{zipcode}")
    List<Business> findBusinessesByProximity(@PathVariable int zipcode) {
        return businessRepository.findByProximity(zipcode);
    }

    @PostMapping("/businesses")
    String addBusiness(@RequestBody Business business) {
        if (business == null) {
            return failure;
        }

        businessRepository.save(business);
        return success;
    }

}
