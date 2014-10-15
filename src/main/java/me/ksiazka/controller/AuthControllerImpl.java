package me.ksiazka.controller;

import me.ksiazka.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("authController")
public class AuthControllerImpl implements AuthController{

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register"ą, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String registerDone(User user){
        //todo
        return "home";
    }
}
