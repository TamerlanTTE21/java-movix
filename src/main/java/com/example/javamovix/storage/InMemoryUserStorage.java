package com.example.javamovix.storage;

import com.example.javamovix.Interface.UserStorage;
import com.example.javamovix.model.User;
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
    public void delete(Integer id) {
        users.remove(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return users.containsKey(id);
    }
}
