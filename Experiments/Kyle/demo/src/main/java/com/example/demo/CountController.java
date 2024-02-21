package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CountController {
    
    Counter counter = new Counter();

    @GetMapping("/count")
    public @ResponseBody int getCount() {
        return counter.getCount();
    }

    @PostMapping("/count+")
    public @ResponseBody String updateCount() {
        counter.increment();
        return "Updated";
    }
}
