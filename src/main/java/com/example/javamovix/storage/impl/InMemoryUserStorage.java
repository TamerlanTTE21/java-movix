package com.example.javamovix.storage.impl;
import com.example.javamovix.model.User;
import com.example.javamovix.storage.UserStorage;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {

    final Map<Integer, User> users = new HashMap<>();
    private int id = 1;

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public User create(User user) {
        user.setId(id++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean existsById(Integer id) {
        return users.containsKey(id);
    }

    @Override
    public User getById(Integer id) {
        return users.get(id);
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        users.get(userId).getFriends().add(friendId);
    }

    @Override
    public void removeFriend(Integer userId, Integer friendId) {
        users.get(userId).getFriends().remove(friendId);
    }
}
