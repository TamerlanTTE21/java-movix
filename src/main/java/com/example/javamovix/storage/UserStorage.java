package com.example.javamovix.storage;
import com.example.javamovix.model.User;
import java.util.Collection;

public interface UserStorage {
    Collection<User> findAll();

    User create(User user);

    User update(User user);

    void addFriend(Integer userId, Integer friendId);

    void removeFriend(Integer userId, Integer friendId);

    boolean existsById(Integer id);

    User getById(Integer id);

}
