package com.example.javamovix.service;

import com.example.javamovix.storage.UserStorage;
import com.example.javamovix.exception.NotFoundException;
import com.example.javamovix.exception.ValidationException;
import com.example.javamovix.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
            throw new NotFoundException("User is not found");
        }
        return userStorage.update(user);
    }

    public void addFriend(Integer userId, Integer friendId) {
        if (userId == null || friendId == null) {
            throw new ValidationException("userId or friendId is null");
        }

        if (userId.equals(friendId)) {
            throw new ValidationException("can't add yourself");
        }

        if (!userStorage.existsById(userId)) {
            throw new NotFoundException("userId not found");
        }
        if (!userStorage.existsById(friendId)) {
            throw new NotFoundException("friendId not found");
        }
        User user = userStorage.getById(userId);
        User friend = userStorage.getById(friendId);

        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
    }

    public void removeFriend(Integer userId, Integer friendId) {
        if (userId == null || friendId == null) {
            throw new ValidationException("userId or friendId is null");
        }

        if (userId.equals(friendId)) {
            throw new ValidationException("can't remove yourself");
        }

        if (!userStorage.existsById(userId)) {
            throw new NotFoundException("userId not found");
        }
        if (!userStorage.existsById(friendId)) {
            throw new NotFoundException("friendId not found");
        }
        User user = userStorage.getById(userId);
        User friend = userStorage.getById(friendId);

        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
    }

    public Collection<User> getUserFriends(Integer userId) {
        if (userId == null) {
            throw new ValidationException("userId is null");
        }

        if (!userStorage.existsById(userId)) {
            throw new NotFoundException("userId not found");
        }

        List<User> result = new ArrayList<>();
        User user = userStorage.getById(userId);
        for (Integer id : user.getFriends()) {
            User friend = userStorage.getById(id);
            result.add(friend);
        }

        return result;
    }

    public Collection<User> getCommonFriends(Integer id, Integer otherId) {
        if (id == null || otherId == null) {
            throw new ValidationException("Id or otherId is null");
        }
        User user = userStorage.getById(id);
        User otherUser = userStorage.getById(otherId);
        if (user == null || otherUser == null) {
            throw new ValidationException("user or otherUser is null");
        }

        Set<Integer> commonFriendsIds = new HashSet<>(user.getFriends());
        commonFriendsIds.retainAll(otherUser.getFriends());
        Collection<User> commonFriends = new ArrayList<>();
        for (Integer friendId : commonFriendsIds) {
            User friend = userStorage.getById(friendId);
            commonFriends.add(friend);
        }
        return commonFriends;
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