package me.ksiazka.service;

import me.ksiazka.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public void includeRoles(User user);
}
