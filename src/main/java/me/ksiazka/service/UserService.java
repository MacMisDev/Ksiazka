package me.ksiazka.service;

import me.ksiazka.model.User;

import java.util.List;

public interface UserService extends BasicServiceInterface<User> {

    public User findUserByEmail(String email);
    public User findUserByEmailWithLists(String email);
    public User getUserWithLists(long id);
    public User findUserByUsername(String username);
}
