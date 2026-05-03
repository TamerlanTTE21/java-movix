package com.example.javamovix.controller;
import com.example.javamovix.model.User;
import com.example.javamovix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);

    }

    @PutMapping("/{userId}/friends/{friendId}")
    public void addFriend(@PathVariable Integer userId,
                          @PathVariable Integer friendId) {
        userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable Integer id,
                             @PathVariable Integer friendId) {
        userService.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public Collection<User> getUserFriends(@PathVariable Integer id) {
        return userService.getUserFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<User> getCommonFriends(@PathVariable Integer id,
                                             @PathVariable Integer otherId) {
        return userService.getCommonFriends(id, otherId);
    }

}


