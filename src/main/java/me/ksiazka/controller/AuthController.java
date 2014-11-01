package me.ksiazka.controller;

import me.ksiazka.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface AuthController {
    public String login();
    public String login(String error, Model model);
    public String register(Model model);
    public String register(User user, BindingResult bindingResult);
}
