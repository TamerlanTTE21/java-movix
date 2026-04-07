package com.example.javamovix.controller;


import com.example.javamovix.model.User;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

public class UserController {
@RestController
    @RequestMapping("/users")
    public class UserControllerResource {
    private final Map<String, User> users = new HashMap<>();

    @GetMapping
    public Map<String, User> getAllUsers() {
        return users;
    }

    @PostMapping
    public Map<String, User> createUser(@RequestBody User user) {
        users.put(user.getEmail(), user);
        return users;
    }

    @PutMapping
    public Map<String, User> updateUser(@RequestBody User user) {
        users.put(user.getEmail(), user);
        return users;
    }
}

}
