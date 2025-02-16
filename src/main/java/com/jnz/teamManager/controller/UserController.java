package com.jnz.teamManager.controller;

import com.jnz.teamManager.entity.Team;
import com.jnz.teamManager.entity.User;
import com.jnz.teamManager.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    //TODO REMOVE LATER IN PRODUCTION, ONLY FOR TESTING
    public Iterable<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/all/{id}")
    public Iterable<User> getAllUsersExceptCaller(@PathVariable("id") Long id){
        return userService.getAllUsersExceptCaller(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }


    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/teams/{id}")
    public Iterable<Team> getTeamsByUserId(@PathVariable("id") Long id){
        return userService.getTeamsByUserId(id);
    }


    @PutMapping("/add_team")
    public void addTeamToUser(@RequestBody Map<String, String> json) {
        val id = Long.parseLong(json.get("id"));
        val teamId = Long.parseLong(json.get("teamId"));
        userService.addTeamToUser(id, teamId);
    }


}