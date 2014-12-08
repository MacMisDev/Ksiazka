package me.ksiazka.controller;

import me.ksiazka.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface AuthController {
    public String login(Principal principal);
    public String login(String error, Model model);
    public String register(Model model);
    public String register(User user, BindingResult bindingResult);
    public String logout(String info, Model model);
}
