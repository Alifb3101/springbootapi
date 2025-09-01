package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class Usercontroller {
    
    private List<User> users = new ArrayList<>();

    // CREATE
    @PostMapping
    public User addUser(@RequestBody User user) {
        users.add(user);
         return user;
    }

    // READ all
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // READ one
    @GetMapping("/{id}")
    public List<User> getUser(@PathVariable int id) {
    return users.stream()
                .filter(u -> u.getId() == id)
                .collect(Collectors.toList());
    }

    // UPDATE
    @PutMapping("/{id}")
    public List<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
         List<User> newuser = new ArrayList<>();
        for (User u : users) {
            if (u.getId() == id) {
                u.setName(updatedUser.getName());
                u.setEmail(updatedUser.getEmail());
                newuser.add(u);
            }
        }
         return newuser;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(u -> u.getId() == id);
        return "User removed with id " + id;
    }
}

// mvnw spring-boot:run