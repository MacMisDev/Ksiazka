package me.ksiazka.controller;

import me.ksiazka.model.User;
import org.springframework.ui.Model;

public interface AuthController {
    public String login();
    public String register(Model model);
    public String registerDone(User user);
}
