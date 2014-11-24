package me.ksiazka.service;

import me.ksiazka.dto.UserDTO;
import me.ksiazka.model.User;

import java.util.List;

public interface UserService extends BasicServiceInterface<User> {

    public User findUserByEmail(String email);
    public UserDTO findUserByEmailDTO(String email);

}
