package com.example.javamovix.service;

import com.example.javamovix.Interface.UserStorage;
import com.example.javamovix.exception.ValidationException;
import com.example.javamovix.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserStorage userStorage;

    public Collection<User> findAllUsers() {
        return userStorage.findAll();
    }

    public User createUser(User user) {
        validateUser(user);
        return userStorage.create(user);
    }

    public User updateUser(User user) {
        validateUser(user);
        if (user.getId() == null) {
            throw new ValidationException("User id is required");
        }
        if (!userStorage.existsById(user.getId())) {
            throw new ValidationException("User is not found");
        }
        return userStorage.update(user);
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

        if (user.getBirthday() == null || user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Birth date is invalid");
        }
    }
}