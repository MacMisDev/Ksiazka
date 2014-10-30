package me.ksiazka.service;

import me.ksiazka.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void saveUser(User user);
}
