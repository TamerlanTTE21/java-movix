package com.example.javamovix.controller;

import com.example.javamovix.exception.ValidationException;
import com.example.javamovix.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    final Collection<User> users = new ArrayList<>();
    private int id = 1;

    @GetMapping
    public Collection<User> findAllUsers() {
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        validateUser(user);
        user.setId(id++);
        users.add(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                user.setId(user.getId());
                return user;
            }
        }

        validateUser(user);
        users.add(user);
        return user;
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new ValidationException("Email address is invalid");
        }

        if (user.getLogin() == null || user.getLogin().contains(" ")) {
            throw new ValidationException("Login is invalid");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Birth date is invalid");
        }

    }
}

