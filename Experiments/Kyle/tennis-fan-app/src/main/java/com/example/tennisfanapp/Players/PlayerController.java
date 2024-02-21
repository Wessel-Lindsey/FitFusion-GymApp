package com.example.tennisfanapp.Players;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlayerController {
    
    ArrayList<Player> players = new ArrayList<Player>();

    @GetMapping("/players")
    public @ResponseBody ArrayList<Player> getPlayers() {
        return players;
    }

    @PostMapping("/player+")
    public @ResponseBody String addPlayer(@RequestBody Player player) {
        players.add(player);
        return "Added " + player.getFirstName();
    }
    
    

}
